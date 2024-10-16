package auto;
import org.testng.Assert;
import org.testng.annotations.Test;

import hu.szit.Client;

public class TypicodeTest {
    
    String url = "http://[::1]:8000/employees";

    @Test(enabled = false)
    public void testGetUsers() {
        Client client = new Client();
        String body = client.get(url);
        boolean ok = body.contains("city");
        System.out.println(body);
        Assert.assertTrue(ok);        
    }

    @Test(enabled = false)
    public void testPostUser() {
        Client client = new Client();
        String body = "{ \"name\": \"Por Péter\" }";
        String res = client.post(url, body);
        boolean ok = res.contains("name");
        Assert.assertTrue(ok);        
    }

    @Test(enabled = false)
    public void testPutUser() {
        Client client = new Client();
        String body = "{ \"name\": \"Por Péter\" }";
        String res = client.put(url + "/1", body);
        boolean ok = res.contains("name");
        Assert.assertTrue(ok);
    }

    @Test(enabled = true)
    public void testDeleteUser() {
        Client client = new Client();        
        String res = client.delete(url + "/2");
        boolean ok = res.contains("{}");
        Assert.assertTrue(ok);
    }

}
