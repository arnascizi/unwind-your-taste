package lt.insoft.arnas.cizikovas.galery.zkgaleryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zk.ui.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ZkGaleryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkGaleryProjectApplication.class, args);
    }

}
