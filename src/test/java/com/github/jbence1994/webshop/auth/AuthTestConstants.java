package com.github.jbence1994.webshop.auth;

public interface AuthTestConstants {
    String ACCESS_TOKEN_AS_STRING = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJqdWhhc3ouYmVuY2UuenNvbHRAZ21haWwuY29tIiwiZmlyc3ROYW1lIjoiQmVuY2UiLCJtaWRkbGVOYW1lIjoiWnNvbHQiLCJsYXN0TmFtZSI6Ikp1aMOhc3oiLCJyb2xlIjoiQURNSU4iLCJleHAiOjI1MzQwMjI5NzE5OX0.zcQx11irvJPnNA18YYuYwZRrEn0vnI1c9NqRPa68gdFSVB8-epFMeBSd3jw_xzP8Z2wiaYHwM1MdPu9cvICx4g";
    String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    String MALFORMED_BEARER_TOKEN = "NotBearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    String INVALID_BEARER_TOKEN = "Bearer invalidToken";
    String JWT_SECRET_KEY = "aV+99UwH+R+cix1hXbDi9id5DnfZXTfnGGciFRDms3JZgqNfevgLvHmei01hBM5zCocViX3H4aGejpW009YWlXfTvLtUa296VmGBemzNqx0=";
    int ACCESS_TOKEN_EXPIRATION = 300;
    int REFRESH_TOKEN_EXPIRATION = 604800;
    String MALFORMED_TOKEN = "this.is.not.a.jwt";
}
