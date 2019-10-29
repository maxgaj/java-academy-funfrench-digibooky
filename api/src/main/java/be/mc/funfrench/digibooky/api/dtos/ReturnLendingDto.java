package be.mc.funfrench.digibooky.api.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "public informations about a specific end of lending")
public class ReturnLendingDto extends LendingDto {

    @ApiModelProperty(notes = "The return message of the lending: informs if the member returned a book too late.")
    private String returnMessage;

    public String getReturnMessage() {
        return returnMessage;
    }

    public ReturnLendingDto withFeeMessage(long days) {
        this.returnMessage = "Your book return is late of " + days + " days.";
        return this;
    }

    public ReturnLendingDto withoutFeeMessage() {
        this.returnMessage = "Thank you for the book return.";
        return this;
    }
}
