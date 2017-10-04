package com.vmantek.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MsgResponse
{
    @JsonProperty("approval-code")
    String approvalCode;

    String rc;

    public String getApprovalCode()
    {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode)
    {
        this.approvalCode = approvalCode;
    }

    public String getRc()
    {
        return rc;
    }

    public void setRc(String rc)
    {
        this.rc = rc;
    }
}
