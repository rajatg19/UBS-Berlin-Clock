package com.ubs.opsit.interviews;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;

import org.junit.Test;

public class BerlinClockJUnitTest {
	
	private static final String NEW_LINE = System.getProperty("line.separator");
	
	
	public TimeConverter m_objBerlinClock ;
	
	@Before
	public void setUp() {
		m_objBerlinClock = new UBSBerlinClock();
    }
	
	@After
	public void release()
	{
		m_objBerlinClock=null;
	}
	
	@Test
	public void BerlinClockNullTest() {		
		Assert.assertEquals(m_objBerlinClock.convertTime(null), "Enter Time in HH:MM:SS format");
	}
	
	@Test
	public void BerlinClockNonNumericTest() {		
		Assert.assertEquals(m_objBerlinClock.convertTime("122345"), "Time format is not proper, Enter time in HH:MM:SS format");
		Assert.assertEquals(m_objBerlinClock.convertTime("12:abchda"), "Time format is not proper, Enter time in HH:MM:SS format");
	}
	@Test
	public void BerlinClockInvalidBoundTest() {		
		Assert.assertEquals(m_objBerlinClock.convertTime("25:20:23"), "Hours should be between 0 and 23");
		Assert.assertEquals(m_objBerlinClock.convertTime("22:70:23"), "Minutes should be between 0 and 59");
		Assert.assertEquals(m_objBerlinClock.convertTime("12:20:73"), "Seconds should be between 0 and 59");
		Assert.assertEquals(m_objBerlinClock.convertTime("24:20:03"), "Entered Time is invalid. Time Should be more than 24:00:00");
	}
	@Test
	public void BerlinClockValidTest() {
		String l_strFirstTest= "O"+NEW_LINE+
				"RROO"+NEW_LINE+
				"RROO"+NEW_LINE+
				"YYRYOOOOOOO"+NEW_LINE+
				"YYYY";
		Assert.assertEquals(m_objBerlinClock.convertTime("12:24:35"), l_strFirstTest);
		String l_strSecondTest= "Y"+NEW_LINE+
				"RROO"+NEW_LINE+
				"OOOO"+NEW_LINE+
				"YYOOOOOOOOO"+NEW_LINE+
				"YYOO";
		Assert.assertEquals(m_objBerlinClock.convertTime("10:12:34"), l_strSecondTest);
		String l_strThirdTest= "Y"+NEW_LINE+
				"OOOO"+NEW_LINE+
				"OOOO"+NEW_LINE+
				"OOOOOOOOOOO"+NEW_LINE+
				"OOOO";
		Assert.assertEquals(m_objBerlinClock.convertTime("00:00:00"), l_strThirdTest);
		String l_strFourthTest= "O" +NEW_LINE+
				"OOOO" + NEW_LINE+
				"ROOO" + NEW_LINE+
				"OOOOOOOOOOO" + NEW_LINE+
				"YYYO";
		Assert.assertEquals(m_objBerlinClock.convertTime("1:3:5"), l_strFourthTest);
	}

}
