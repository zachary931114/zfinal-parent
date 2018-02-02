package me.zhoubl.zfinal.web.common.shiro;

import me.zhoubl.zfinal.common.utils.UtilAES;
import me.zhoubl.zfinal.common.utils.UtilCodec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


/**
 * account shiro 密码校验 Created by zhoubl on 2017年2月22日.
 */
public class SysUserCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		Object tokenCredentials = null;
		try {
			tokenCredentials = UtilCodec.pwdCodec(UtilAES.AesCbcDecode(Hex.decodeHex(String.valueOf(usernamePasswordToken.getPassword()).toCharArray()), UtilAES.restoreSecretKey(UtilAES.KEY.getBytes()), UtilAES.IV.getBytes()),
					usernamePasswordToken.getUsername());
		} catch (DecoderException e) {}
		Object accountCredentials = getCredentials(info);
		return equals(tokenCredentials, accountCredentials);
	}
}
