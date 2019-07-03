package com.se231.onlineedu;


import com.se231.onlineedu.jwtTest.JwtProviderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SignInAndSignUpTests.class, JwtProviderTest.class})
public class TestSuiteCase {
}
