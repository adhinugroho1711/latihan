package com.vmantek.demo;

import com.vmantek.chimera.Q2Service;
import com.vmantek.chimera.SysDeployer;
import org.jpos.iso.MUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

@SpringBootApplication
public class Main
{
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception
    {
        new Main().process(args);
    }

    public ConfigurableApplicationContext process(String[] args)
    {
        System.setProperty("HIBERNATE_CFG", "/hibernate.cfg.xml");
        System.setProperty("spring.config.location", "file:cfg/");
        System.setProperty("java.net.preferIPv4Stack", "true");
        ensureDirsExists("./db", "./cfg", "./log");
        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        return app.run(args);
    }

    private static void ensureDirsExists(String... dirs)
    {
        for (String dir : dirs)
        {
            File d = new File(dir);
            if (!d.exists())
            {
                if (!d.mkdirs())
                {
                    log.warn("Could not create directory: " + dir);
                }
            }
        }
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    SysDeployer sysDeployer()
    {
        return new SysDeployer();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    Q2Service getQ2(SysDeployer sysDeployer)
    {
        return new Q2Service(sysDeployer.getBaseDir());
    }
}
