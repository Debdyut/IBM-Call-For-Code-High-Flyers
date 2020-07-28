

package com.hackathon.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Utils API error response object
 */
@ApiModel(description = "Utils API error response object", value = "errorResponses")
public class ErrorResponses {
    @JsonProperty("code")
    private String code = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("developerMessage")
    private String developerMessage = null;

    @JsonProperty("moreInfo")
    private Object moreInfo = null;

    @JsonProperty("refreshIndicator")
    private boolean refreshIndicator=false;

    @JsonProperty("appVersion")
    private String appVersion=null;

    public ErrorResponses code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Error Code
     * 
     * @return code
     **/
    @ApiModelProperty(value = "Error Code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorResponses message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Error message.
     * 
     * @return message
     **/
    @ApiModelProperty(value = "Error message.")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponses developerMessage(String developerMessage) {
        this.developerMessage = developerMessage;
        return this;
    }

    /**
     * Status depending on the success/failure of the call.
     * 
     * @return developerMessage
     **/
    @ApiModelProperty(value = "Status depending on the success/failure of the call.")
    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public ErrorResponses moreInfo(Object moreInfo) {
        this.moreInfo = moreInfo;
        return this;
    }

    /**
     * Status depending on the success/failure of the call.
     * 
     * @return moreInfo
     **/
    @ApiModelProperty(value = "Status depending on the success/failure of the call.")
    public Object getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(Object moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appVersion == null) ? 0 : appVersion.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((developerMessage == null) ? 0 : developerMessage.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((moreInfo == null) ? 0 : moreInfo.hashCode());
        result = prime * result + (refreshIndicator ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorResponses other = (ErrorResponses) obj;
        if (appVersion == null) {
            if (other.appVersion != null)
                return false;
        } else if (!appVersion.equals(other.appVersion))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (developerMessage == null) {
            if (other.developerMessage != null)
                return false;
        } else if (!developerMessage.equals(other.developerMessage))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (moreInfo == null) {
            if (other.moreInfo != null)
                return false;
        } else if (!moreInfo.equals(other.moreInfo))
            return false;
        if (refreshIndicator != other.refreshIndicator)
            return false;
        return true;
    }

    public boolean isRefreshIndicator() {
        return refreshIndicator;
    }

    public void setRefreshIndicator(boolean refreshIndicator) {
        this.refreshIndicator = refreshIndicator;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String toString() {
        return "ErrorResponses [code=" + code + ", message=" + message + ", developerMessage=" + developerMessage
                + ", moreInfo=" + moreInfo + ", refreshIndicator=" + refreshIndicator + ", appVersion=" + appVersion
                + "]";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    public String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
