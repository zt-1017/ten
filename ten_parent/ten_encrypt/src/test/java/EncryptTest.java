
import com.ten.encrypt.EncryptApplication;
import com.ten.encrypt.rsa.RsaKeys;
import com.ten.encrypt.service.RsaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EncryptApplication.class)
public class EncryptTest {

    @Autowired
    private RsaService rsaService;

    @Before
    public void before() throws Exception{
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void genEncryptDataByPubKey() {
        String data = "{\"title\":\"java\"}";

        try {

            String encData = rsaService.RSAEncryptDataPEM(data, RsaKeys.getServerPubKey());

            System.out.println("data: " + data);
            System.out.println("encData: " + encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws Exception {
        String requestData = "DrEraK+OKg3Ww3/MoRvUmuWxqxT0PghRtsRIWxk70y6KT+dgkU2ovFP9L1k6FS2v9iJqNufKM9f/zIkmXjIzfEzQc5TVWpQX5fKNgNTteCTwqeO1MaDLL+g7IJRZthxBsQtBGvTQF0V7L5HdtlTd9idY9TPj59mnu1wS46gJ+dDQ0V3jWyc1Sxi/CHQzdSnaDd9+a7vyV3+0hHlxRDsVlamAnIgvsIFYsLqN3/9H8kFzyH7wANF03uWRNQ0wv6kZlMfd9LS6/wdyS5Od0m7zyXCXf6nsQchuNdv6JQw27ACPhDcxXPCY5bkzGdjYRYgUbSh8uRS2bzKNITDqIxY8dw==";

        String decryptDataPEM = rsaService.RSADecryptDataPEM(requestData, RsaKeys.getServerPrvKeyPkcs8());

        System.out.println(decryptDataPEM);
    }
}
