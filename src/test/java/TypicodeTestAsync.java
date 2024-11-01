import org.testng.Assert;
import org.testng.annotations.Test;

import hu.szit.ResClientAsync;

public class TypicodeTestAsync {
    
    String url1 = "http://[::1]:8000/employees";
    String url2 = "https://jsonplaceholder.typicode.com/users";
    String url = url2;

    @Test(enabled = true)
    public void testGetUsers() {
        ResClientAsync client = new ResClientAsync();
        String body = client.get(url).join();
        boolean ok = body.contains("city");
        System.out.println(body);
        Assert.assertTrue(ok);        
    }

    @Test(enabled = true)
    public void testPostUser() {
        ResClientAsync client = new ResClientAsync();
        String body = "{ \"name\": \"Por Péter\" }";
        String res = client.post(url, body).join();
        boolean ok = res.contains("name");
        Assert.assertTrue(ok);        
    }

    @Test(enabled = true)
    public void testPutUser() {
        ResClientAsync client = new ResClientAsync();
        String body = "{ \"name\": \"Por Péter\" }";
        String res = client.put(url + "/1", body).join();
        boolean ok = res.contains("name");
        Assert.assertTrue(ok);
    }

    @Test(enabled = true)
    public void testDeleteUser() {
        ResClientAsync client = new ResClientAsync();
        String res = client.delete(url + "/2").join();
        boolean ok = res.contains("{}");
        Assert.assertTrue(ok);
    }

}

