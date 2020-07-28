package com.hackathon.cnfg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import com.hackathon.utils.ErrorResponses;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerCnfg  {

    private static final String API_ERROR = "errorResponses";

    @SuppressWarnings("unused")
	private static final String HEADER = "header";
    @SuppressWarnings("unused")
	private static final String STRING = "string";
    @Autowired
    TypeResolver resolver;

    private ApiInfo apiInfoV1() {
        String description = "These are version 1 APIs";
        return new ApiInfoBuilder().title("Base Template Version 1 APIs").description(description)
                .version("1").build();
    }

    private ApiInfo apiInfoV2() {
        String description = "These are version 2 APIs";
        return new ApiInfoBuilder().title("Base Template Version 2 APIs").description(description)
                .version("2").build();
    }

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(headerParameters())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages())
                .globalResponseMessage(RequestMethod.OPTIONS, responseMessages())
                .useDefaultResponseMessages(false).groupName("v1").apiInfo(apiInfoV1())
                .additionalModels(resolver.resolve(ErrorResponses.class)).select()
                .apis(Predicates
                        .not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.regex("/.*")).build();
    }

    @Bean
    public Docket apiV2() {
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(headerParameters())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages())
                .globalResponseMessage(RequestMethod.OPTIONS, responseMessages())
                .useDefaultResponseMessages(false).groupName("v2").apiInfo(apiInfoV2())
                .additionalModels(resolver.resolve(ErrorResponses.class)).select()
                .apis(Predicates
                        .not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.regex("/.*")).build();
    }

    @SuppressWarnings("unused")
	private Parameter getParameter(String name, String description, String modelRefType,
            String parameterType, boolean required) {

        return new ParameterBuilder().name(name).description(description)
                .modelRef(new ModelRef(modelRefType)).parameterType(parameterType)
                .required(required).build();
    }

    private List<Parameter> headerParameters() {
        List<Parameter> operationParameters = new ArrayList<>();

        /*
         * Parameter applicationOwnerCodeParam =
         * getParameter("applicationOwnerCode",
         * "Application owner code from CRU.APCN_OWNR Table", STRING, HEADER,
         * true); operationParameters.add(applicationOwnerCodeParam); Parameter
         * requestedEmployeeIdParam = getParameter("requestedEmployeeId",
         * "ID of employee who requesting details on behalf of other employee",
         * STRING, HEADER, false);
         * operationParameters.add(requestedEmployeeIdParam); Parameter
         * apiResourceNameParam = getParameter("apiResourceName",
         * "CRU API/resource being invoked", STRING, HEADER, true);
         * operationParameters.add(apiResourceNameParam); Parameter
         * apiRequestUtcTsParam = getParameter("apiRequestUtcTs",
         * "Request Date Time", STRING, HEADER, true);
         * operationParameters.add(apiRequestUtcTsParam); Parameter
         * applicationNameParam = getParameter("applicationName", "Name of APP",
         * STRING, HEADER, true); operationParameters.add(applicationNameParam);
         * Parameter applicationVersionNumParam =
         * getParameter("applicationVersionNum", "Version of APP invoking API",
         * STRING, HEADER, true);
         * operationParameters.add(applicationVersionNumParam); Parameter
         * applicationDeviceNameParam = getParameter("applicationDeviceName",
         * "Name of Device request came from", STRING, HEADER, true);
         * operationParameters.add(applicationDeviceNameParam); Parameter
         * applicationDeviceOSParam = getParameter("applicationDeviceOsName",
         * "Operating System for Device", STRING, HEADER, true);
         * operationParameters.add(applicationDeviceOSParam); Parameter
         * applicationDeviceMacAddressParam =
         * getParameter("applicationDeviceMacAdr", "MAC address of device",
         * STRING, HEADER, true);
         * operationParameters.add(applicationDeviceMacAddressParam); Parameter
         * networkIpAddressParam = getParameter("networkIpAdr",
         * "IP address of network", STRING, HEADER, true);
         * operationParameters.add(networkIpAddressParam); Parameter
         * apiConsumerRecordTextParam = getParameter("apiConsumerRecordText",
         * "This is Pipe seperated request various parameters may be part of Path/Query Param"
         * , STRING, HEADER, true);
         * operationParameters.add(apiConsumerRecordTextParam); Parameter
         * authorizationParam = getParameter("Authorization",
         * "Authorization code provided by APIGEE", STRING, HEADER, true);
         * operationParameters.add(authorizationParam); Parameter
         * transactionIdParam = getParameter("TransactionId",
         * "Transaction ID generated by APIGEE/Consumer - This is what LOGGING API will primarily Pick"
         * , STRING, HEADER, true); operationParameters.add(transactionIdParam);
         * Parameter contentTypeParam = getParameter("Content-Type",
         * "The MIME Type for the request.Example: application/json or text/xml"
         * , STRING, HEADER, false); operationParameters.add(contentTypeParam);
         * Parameter acceptParam = getParameter("Accept",
         * "The format(MIME Type) of the response.Example: application/json or text/xml"
         * , STRING, HEADER, false); operationParameters.add(acceptParam);
         * Parameter employeeIdParam = getParameter("employeeId",
         * "Employee ID for user whose information requested to API", STRING,
         * HEADER, true); operationParameters.add(employeeIdParam);
         */

        return operationParameters;
    }

    private List<ResponseMessage> responseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();

        ResponseMessage badRequestRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(badRequestRespMsg);

        ResponseMessage unauthorizedRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(unauthorizedRespMsg);

        ResponseMessage forbiddenRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.FORBIDDEN.value()).message(HttpStatus.FORBIDDEN.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(forbiddenRespMsg);

        ResponseMessage notFoundRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(notFoundRespMsg);

        ResponseMessage unsupportedMediaTypeRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(unsupportedMediaTypeRespMsg);

        ResponseMessage internalServerErrorRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(internalServerErrorRespMsg);

        ResponseMessage serviceUnavailableRespMsg = new ResponseMessageBuilder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                .message(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                .responseModel(new ModelRef(API_ERROR)).build();
        responseMessages.add(serviceUnavailableRespMsg);

        return responseMessages;
    }
}
