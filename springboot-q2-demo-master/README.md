This demo shows jPOS Q2 running inside Spring Boot as a component and exposes a RestController to do a sample operation on a MUX.

## Operation

Checkout the code and do:

```
   gradle bootRun
```

Upon startup you'll be able to use curl/http/wget to access URL:

```
   http get http://localhost:8085/api/echo
```

Which would return something like:

```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sat, 02 Jul 2016 10:22:20 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked

{
    "approval-code": "268350",
    "rc": "00"
}
```
