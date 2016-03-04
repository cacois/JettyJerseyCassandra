//package org.cacois.JettyJerseyCassandra.resources;
//
//import static org.mockito.Mockito.*;
//import static org.junit.Assert.assertEquals;
//
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.Response;
//
//import org.glassfish.hk2.utilities.binding.AbstractBinder;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import org.japhar81.jerseyStack.services.StringService;
//import org.junit.Test;
//
//public class HelloWorldResourceTest extends JerseyTest {
//    @Override
//    protected Application configure() {
//        return new ResourceConfig() {
//            {
//                register(new TestBinder());
//                register(HelloWorldResource.class);
//            }
//        };
//    }
//
//    @Test
//    public void get() {
//        Response response = target("hello/test").request().get();
//        assertEquals("Hello test", response.readEntity(String.class));
//    }
//
//    public static class TestBinder extends AbstractBinder {
//        @Override
//        protected void configure() {
//            StringService stringService = mock(StringService.class);
//            when(stringService.getString()).thenReturn("Mock String");
//            bind(stringService).to(StringService.class);
//        }
//    }
//}
