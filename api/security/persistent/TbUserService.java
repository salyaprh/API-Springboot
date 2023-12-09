package com.api.security.persistent;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.api.security.AES;
import com.api.security.TokenFields;
import com.api.security.ValidateToken;


@Service
public class TbUserService {
	private TbUserRepository repository;
	private TokenSessionRepository tokenRepository;
	
	public TbUserService(TbUserRepository repository, TokenSessionRepository tokenRepository) {
		this.repository = repository;
		this.tokenRepository = tokenRepository;
	}
	
	public TbUser readUser(String id) {
		Optional<TbUser> option = repository.findById(id);
		if (option.isEmpty())
			return null;
		else	
			return option.get();		
	}

	/**
	 * 1. Create object user dari class TbUser
	 * 2. Enkripsi field password dari API sebelum diset kedalam field password
	 * 3. Buat global token untuk setiap user
	 * 4. Atur tanggal expired token (misal 3 bulan)
	 * @param id
	 * @param userName
	 * @param password
	 */
	public void createUser(String id, String userName, String password) {
		TbUser user = new TbUser();
		try {
			user.setId(id);
			user.setNama(userName);					
			user.setPassword(AES.encrypt(password, ValidateToken.globalSecretKey));
			user.setSession_token(ValidateToken.createToken(user.getPassword()));
			user.setToken(ValidateToken.createToken(user.getPassword()));
			user.setValid_token(LocalDate.now().plusMonths(2));
			repository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1. Cari data user ke database di tabel user, jika tidak ketemu kembalikan null ke API
	 * 2. Bandingkan password dari front-end dengan password terenkripsi dari tabel user (setelah di-dekripsi)
	 * 3. Jika sesuai, inisialisasi field token, simpan token di tabel token_session untuk validasi berikutnya
	 * 4. Kembalikan nilai di object TokenSession ke API
	 * @param id
	 * @param password
	 * @return
	 */
	public TokenFields login(String id, String password) {
		TbUser user = null;
		TokenFields token = new TokenFields();
		Optional<TbUser> option = repository.findById(id);
		if (option.isEmpty())
			return null;
		else	
			user = option.get();
		
		String s = AES.decrypt(user.getPassword(), ValidateToken.globalSecretKey);
		String tk = ValidateToken.createToken(user.getId()+LocalTime.now().toString());
		if (password.equalsIgnoreCase(s)){
			token.setAlgorithm("AES");
			token.setFlag("true");
			token.setSessionToken(tk);
			tokenRepository.save(createTokenSession(user, tk));

			return token;
		}
		return null;
	}
	
	private TokenSession createTokenSession(TbUser user, String tkn) {
		TokenSession ts = new TokenSession();
		ts.setTokenId(user.getId());		
		ts.setWaktuDibuat(LocalDateTime.now());
		ts.setExpired(LocalDateTime.now().plusMinutes(2));
		ts.setToken(tkn);
		return ts;
	}
	
    public boolean validateToken(TokenFields token) {
    	boolean flag=true;
    	Optional<TokenSession> dataToken = tokenRepository.findById(token.getTokenHolder());
    	
    	if (dataToken.isPresent()) {
    		if (!token.getSessionToken().equals(dataToken.get().getToken())) {
    			System.out.println("--- invalid token -----");
    			flag = false;
    		}
    		System.out.println(dataToken.get().getExpired().compareTo(LocalDateTime.now()));
    		if (dataToken.get().getExpired().compareTo(LocalDateTime.now()) < 0) {
    			System.out.println("--- expired token, tf-> "+dataToken.get().getExpired()+", now-> "+LocalDateTime.now());
    			flag = false;
    		}
    	}
    	return flag;
    	
    }

}

