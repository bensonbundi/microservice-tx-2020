package net.tospay.transaction.models.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import net.tospay.transaction.enums.AccountType;
import net.tospay.transaction.enums.SourceType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "user_id",
        "user_type",
        "account",
        "amount"
})
public class TopupValue implements Serializable
{
    private final static long serialVersionUID = -9078608771772465581L;

    @JsonProperty("type")
    private SourceType type;

    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("user_type")
    private AccountType userType;

    @JsonProperty("account")
    private String account;

    @JsonProperty("amount")
    private Double amount;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public SourceType getType()
    {
        return type;
    }

    public void setType(SourceType type)
    {
        this.type = type;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public AccountType getUserType()
    {
        return userType;
    }

    public void setUserType(AccountType userType)
    {
        this.userType = userType;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    @JsonProperty("account")
    public String getAccount()
    {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account)
    {
        this.account = account;
    }

    public TopupValue withAccount(String account)
    {
        this.account = account;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties)
    {
        this.additionalProperties = additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }

    public TopupValue withAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
        return this;
    }
}