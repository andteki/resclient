package hu.szit;
import org.testng.Assert;
import org.testng.annotations.Test;

import hu.szit.resclient.ResClient;

public class TypicodeTest {
    
    String url1 = "http://[::1]:8000/employees";
    String url2 = "https://jsonplaceholder.typicode.com/users";
    String url = url2;

    @Test(enabled = true)
    public void testGetUsers() {
        ResClient client = new ResClient();
        String body = client.get(url);
        boolean ok = body.contains("city");
        System.out.println(body);
        Assert.assertTrue(ok);        
    }

    @Test(enabled = true)
    public void testPostUser() {
        ResClient client = new ResClient();
        String body = "{ \"name\": \"Por Péter\" }";
        String res = client.post(url, body);
        boolean ok = res.contains("name");
        Assert.assertTrue(ok);        
    }

    @Test(enabled = true)
    public void testPutUser() {
        ResClient client = new ResClient();
        String body = "{ \"name\": \"Por Péter\" }";
        String res = client.put(url + "/1", body);
        boolean ok = res.contains("name");
        Assert.assertTrue(ok);
    }

    @Test(enabled = true)
    public void testDeleteUser() {
        ResClient client = new ResClient();        
        String res = client.delete(url + "/2");
        boolean ok = res.contains("{}");
        Assert.assertTrue(ok);
    }

}

