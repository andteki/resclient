# resclient

Java Client for REST API.

The get(), post(), put() and delete() methods can also work with authentication. In the header the word "Bearer" is passed, which is default not set up.

Usage dependencies:

* gson

For testing dependencies:

* testng

## Documentation

* [https://andteki.github.io/resclient/](https://andteki.github.io/resclient/)

The client class is capable of token-based authentication, which is currently not documented.

## Download

JAR fájl:

* [https://github.com/andteki/resclient/releases/](https://github.com/andteki/resclient/releases/)

Forrás:

```bash
git clone https://github.com/andteki/resclient.git
```

## Maven

```xml
<dependency>
    <groupId>hu.szit</groupId>
    <artifactId>resclient</artifactId>
    <version>1.3.2</version>
</dependency>
```

## module-info.java

```java
module valami {
    requires hu.szit.resclient;
    opens com.example to hu.szit.resclient;
}
```

## Example

In the example below, I am using the User class.

### REST API access

Let's reference the restclient jar file.

REST API request with GET method:

```java
String url = "http://foo/bar";
ResClient client = new ResClient();
String result = client.get(url);
System.out.println(result);
```

Adding a new user, using the POST method:

```java
String url = "http://foo/bar";
ResClient client = new ResClient();
String body = "{ \"username\": \"Por Peter\" }";
String result = client.post(url, body);
System.out.println(result);
```

Update a user, using the PUT method:

```java
String url = "http://foo/bar";
ResClient client = new ResClient();
String body = "{ \"username\": \"Por Peter\" }";
String result = client.put(url + "/1", body);
System.out.println(result);
```

Delete a user, using the DELETE method:

```java
String url = "http://foo/bar";
ResClient client = new ResClient();
String result = client.delete(url + "/1");
System.out.println(result);
```

### RESAT API access asynchronous

REST API request with GET method:

```java
String url = "http://foo/bar";
ResClientAsync client = new ResClientAsync();
String result = client.get(url).join();
System.out.println(result);
```

Adding a new user, using the POST method:

```java
String url = "http://foo/bar";
ResClientAsync client = new ResClientAsync();
String body = "{ \"username\": \"Por Peter\" }";
String result = client.post(url, body).join();
System.out.println(result);
```

Update a user, using the PUT method:

```java
String url = "http://foo/bar";
ResClientAsync client = new ResClientAsync();
String body = "{ \"username\": \"Por Peter\" }";
String result = client.put(url + "/1", body).join();
System.out.println(result);
```

Delete a user, using the DELETE method:

```java
String url = "http://foo/bar";
ResClientAsync client = new ResClientAsync();
String result = client.delete(url + "/1").join();
System.out.println(result);
```

### JSON and Java object conversion

Convert JSON string to Java object.

Create a model class.

```java
public class User {    
    String name;
    String username;    
}
```

Conversion:

```java
String url = "http://foo/bar";
ResClient client = new ResClient();
String result = client.get(url);
ArrayList<User> userList = ResConvert.toListObject(result, User.class);
for(User user : userList) {
    System.out.println(user.name);
}
```

Convert to simple object:

```java
String host = "http://foo/bar";
String url = host + "/1";
ResClient client = new ResClient();
String result = client.get(url);
User user = ResConvert.toObject(result, User.class);        
System.out.println(user.name);
```

Convert Java list to JSON string:

In teh User class, you need a constructor that takes name and username:

```java
public class User {    
    String name;
    String username;
    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }    
}
```

Convert Java list to JSON string:

```java
ArrayList<User> userList = new ArrayList<>();
userList.add(new User("Strong Steven", "strongs"));
userList.add(new User("Little Mary", "littlem"));
String json = ResConvert.listToJson(userList);
System.out.println(json);
```

Convert Java object to JSON string:

```java
User user = new User("Erős István", "erosi");        
String json = Convert.toJson(user);
System.out.println(json);
```

### License

Gson is released under the [Apache 2.0 license](LICENSE).

```txt
Copyright 2024 Sallai András

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
