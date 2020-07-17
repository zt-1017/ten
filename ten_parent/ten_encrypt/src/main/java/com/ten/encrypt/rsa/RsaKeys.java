package com.ten.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyEfo98pRV3sPviWaLO/8" +
			"VEtxXrYrlTiciPwVHv9HhfnDbP7CghlO+PLYDpXafJ7OdXYoVY9XSXlcfzFC61Fc" +
			"GgNheIU8UZOhnxs1SUiUrQHMwwFU2GnFMS8Ua+iHfCwPBOXrIygFScqmrmNqoETl" +
			"ZxEiuwAJWCbk3yNHvF5IgGqDBlrc8wMT+KjdG4xQMOzOejsx1jcbB2jJGnm3q9ED" +
			"eWC+S4Q8X1roWt2zUzS5oRxt00pFIHJqoKHzrBwclTNdiyzT91Q4RyzLHgv/F1vW" +
			"VgFfkOAL5Z/LaQmbTgAMDxHG+Tdye5/vHJdZ72pJTUXrzh8+dnYqb1OmPvEudHyt" +
			"2wIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIR+j3ylFXew++" +
			"JZos7/xUS3FetiuVOJyI/BUe/0eF+cNs/sKCGU748tgOldp8ns51dihVj1dJeVx/" +
			"MULrUVwaA2F4hTxRk6GfGzVJSJStAczDAVTYacUxLxRr6Id8LA8E5esjKAVJyqau" +
			"Y2qgROVnESK7AAlYJuTfI0e8XkiAaoMGWtzzAxP4qN0bjFAw7M56OzHWNxsHaMka" +
			"eber0QN5YL5LhDxfWuha3bNTNLmhHG3TSkUgcmqgofOsHByVM12LLNP3VDhHLMse" +
			"C/8XW9ZWAV+Q4Avln8tpCZtOAAwPEcb5N3J7n+8cl1nvaklNRevOHz52dipvU6Y+" +
			"8S50fK3bAgMBAAECggEAWTnYK1SCKpQTrmW4Kx77bl5x8nz8K62d51VO35FhPunH" +
			"y64v36AA0930rYLLZMunNSNf0sZaX8xmje/XXVjQzHIHIZdSCRHZZjXL5oskWXM8" +
			"0v+r2gI9LK1Jzs04VvSV+FuqBu6UdIyI95Yo8Z/gB6vVqjPY9eHWAycHlX73k+WV" +
			"d7eKQFEc9mfOehTnE4aWT5DoEcxEugeKjJCPAD2TfX7TmNYrPS1/ruNMmRWQbFfC" +
			"gwfBFaZwtKI/i5ihOH54cZpbHXlW0rvRK/dKPNcUfnJIJtwq5YUqCTk4ARpNrvso" +
			"q61tYiPx5R8jBsIAbrc55/QjRnGAMdwsJ8xCsZnjUQKBgQD1HmYuhh4iw+PLmzVy" +
			"weUYXnWAItS660CQgGwh3upQhZPi1XJwwhOX1iVBEZWyszT4cmDaqBtvYSjjDI8D" +
			"rkBTf6ifuzww1MUpkHRO02pv468gSVxRZNUUf1jJrs42C6pIM2BrqFyw7iwz0m7M" +
			"IWnBaIinhb5aleeuNQIRKkUnAwKBgQDRK/XkQOblIMWfLRDpjCAYv1Z71SNhn86A" +
			"aTSZEFk4dmEvZEXBB0BPYkwhcmVzp1Ql8S2zRHaqfCpjTF4MtqhlyL/qsKbpAy+B" +
			"/Xft0NejBS3LE1MRcNef+yeEsnkNP9W62A8dfFDpdI/9plMP81XneAtna9lugyZi" +
			"vvR8IoTaSQKBgQDuS/Ro7Gglwi0WRzjp7MhtLX+r57DKW2T0VnVJZG6Fg6cnEf1k" +
			"07i6lyy2moLfLMulcYTpbh7l5B4cxZvPpzrQMj5bs2oryt42r0HzVCOYzU/24nsG" +
			"gjGI8Nj/kZrV/fc4pAHsB8y9EX8VjlG60A0BQz//JM3PQQbwXIY66844wwKBgA09" +
			"Lw8HXJXcASYWACAIEm/+jEbCMr8XEkbmYcHeWwdL1tLdEd0pzFQ58H+dcfZj/Nt5" +
			"7F6QUsJvoW8vgjRjyzzF+/+qtqXA4dzkYhbXRQ64YTGdQ4CzF7K0fsfTU/4WsDxn" +
			"OG2xVJ2yFvsC7/bue91up6ogFV79Rgyx8V95/9ahAoGBAOq45kPnXMDHQu6N+9xt" +
			"a5ZnUVp1lAEUknxsMhzimFn1+K2E5NE4gQ3z0DT+TO0WAepaD4L9OsbfHdLGWMXz" +
			"xskPwj0QhZPvDLauMHocKd4mxUkOCXG8XSZ49Auqs9hDM/mjvI/i2df8G0FylD3c" +
			"jWyDZ9nTnXOXHYwWfD7db9mI";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
