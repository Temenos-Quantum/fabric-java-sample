package com.acme.services;

import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;
import org.testng.annotations.*;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class HelloWorldTest {

	DataControllerRequest request;
	HashMap<String, Object> headers;

	DataControllerResponse response;

	HashMap<String, Object> cfgMap;
	HashMap<String, Object> inMap;
	HashMap<String, Object> outMap;
	Object[] maps;

	@BeforeTest
	public void mockInvocation(){

		//Let's initialise the request, response, config, inputs, outputs and headers to pretend this is a Fabric runtime.

		//Mock the request.
		request = mock(DataControllerRequest.class);
		headers = (HashMap<String, Object>)request.getHeaderMap();

		//Mock the response.
		response = mock(DataControllerResponse.class);

		cfgMap = new HashMap <String, Object>(); //Configurations.
		inMap = new HashMap <String, Object>(); //Input parameters.
		outMap = new HashMap <String, Object>(); //Output parameters.
		maps = new Object[]{cfgMap, inMap, outMap};
	}

	@Test(testName = "Test hello", enabled=true)
	public void testHello() throws Exception {

		HelloWorld hw = new HelloWorld();
		Result result = hw.invoke("sayHello", maps, request, response);
		assertEquals(result.getParamByName("message").getValue(), "Hello World!");
	}

	@Test(testName = "Test goodbye", enabled=false)
	public void testGoodbye() throws Exception {

		HelloWorld hw = new HelloWorld();
		Result result = hw.invoke("sayGoodbye", maps, request, response);
		assertEquals(result.getParamByName("message").getValue(), "Goodbye World!");
	}
}