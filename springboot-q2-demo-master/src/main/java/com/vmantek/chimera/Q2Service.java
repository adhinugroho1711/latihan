package com.vmantek.chimera;

import org.jpos.q2.Q2;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Q2Service
{
    Q2 q2;
    String baseDir;

    public Q2Service(String baseDir)
    {
        this.baseDir = baseDir;
    }

    public Q2 getQ2()
    {
        return q2;
    }

    public void start() throws Exception
    {
        try
        {
            String[] xargs = new String[]{"-r", "-d", new File(baseDir, "deploy").getAbsolutePath()};
            q2 = new Q2(xargs);
            q2.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public void stop() throws Exception
    {
        try
        {
            if (q2 != null)
            {
                q2.shutdown(true);
            }
        }
        catch (Exception ignored)
        {
        }
        finally
        {
            q2 = null;
        }
    }
}
