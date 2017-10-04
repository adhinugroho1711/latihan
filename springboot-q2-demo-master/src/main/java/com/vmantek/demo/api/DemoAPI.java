package com.vmantek.demo.api;

import com.vmantek.demo.domain.MsgResponse;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.MUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.space.SpaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DemoAPI
{
    private static final Random random = new Random (System.currentTimeMillis());

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public MsgResponse sendEcho() throws Exception
    {
        ISOMsg m=new ISOMsg("0800");
        m.set (11, ISOUtil.zeropad (Math.abs(random.nextInt()) % 1000000, 6));
        m.set(70,"301");
        MUX mux = QMUX.getMUX("SIMULATOR");
        ISOMsg rsp=mux.request(m,30000);
        MsgResponse r=new MsgResponse();
        r.setApprovalCode(rsp.getString(38));
        r.setRc(rsp.getString(39));
        return r;
    }
}
