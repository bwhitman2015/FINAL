package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	///TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score

	@Test
	public void AmountTest() {
		double testPayment = RateBLL.getPayment((.08)/12, 360, 300000, 15, false);
		System.out.println(testPayment);
		assertEquals(testPayment,((1400)),0.01);
	}
	
	@Test
	public void rateTest(){
		try {
			assertEquals(RateBLL.getRate(745), .75, 0.001);
		} catch (RateException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = RateException.class)
	public void falseTest() throws RateException {
		RateBLL.getRate(599);
	}
	}

}
