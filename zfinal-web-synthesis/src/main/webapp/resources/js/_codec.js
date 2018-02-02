var k = CryptoJS.enc.Utf8.parse("zhoubl199311140x");
var i = CryptoJS.enc.Utf8.parse('zhoubl199311140x');

function aes(val) {
	var encrypted = CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(val), k, {
		iv : i,
		mode : CryptoJS.mode.CBC,
		padding : CryptoJS.pad.Pkcs7
	});
	return encrypted.ciphertext.toString();
}