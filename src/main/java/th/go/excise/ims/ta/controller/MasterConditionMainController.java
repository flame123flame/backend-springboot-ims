package th.go.excise.ims.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.service.MasterConditionMainService;
import th.go.excise.ims.ta.vo.ConditionMessageVo;
import th.go.excise.ims.ta.vo.MasCondMainRequestVo;
import th.go.excise.ims.ta.vo.MasCondMainResponseVo;
import th.go.excise.ims.ta.vo.MasterConditionMainHdrDtlVo;
import th.go.excise.ims.ta.vo.Ta010101Vo;
import th.go.excise.ims.ta.vo.TaMasCondMainHdrForm;

@Controller
@RequestMapping("/api/ta/master-condition-main")
public class MasterConditionMainController {

    private static final Logger logger = LoggerFactory.getLogger(MasterConditionMainController.class);

    @Autowired
    private MasterConditionMainService masterConditionService;

    @PostMapping("/create-hdr")
    @ResponseBody
    public ResponseData<TaMasCondMainHdr> insertCondMainHdr(@RequestBody TaMasCondMainHdrForm form) {
        ResponseData<TaMasCondMainHdr> response = new ResponseData<TaMasCondMainHdr>();
        try {
            masterConditionService.insertCondMainHdr(form);
            response.setData(null);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @PostMapping("/update-hdr")
    @ResponseBody
    public ResponseData<TaMasCondMainHdr> updateCondMainHdr(@RequestBody TaMasCondMainHdrForm form) {
        ResponseData<TaMasCondMainHdr> response = new ResponseData<TaMasCondMainHdr>();
        try {
            masterConditionService.updateCondMainHdr(form);
            response.setData(null);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
    @PostMapping("/delete")
    @ResponseBody
    public ResponseData<TaMasCondMainHdr> deleteCondMain(@RequestBody TaMasCondMainHdr form) {
        ResponseData<TaMasCondMainHdr> response = new ResponseData<TaMasCondMainHdr>();
        try {
            masterConditionService.deleteCondMain(form);
            response.setData(null);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @PostMapping("/create-dtl")
    @ResponseBody
    public ResponseData<List<MasterConditionMainHdrDtlVo>> insertCondMainDtl(@RequestBody MasterConditionMainHdrDtlVo formVo) {
        ResponseData<List<MasterConditionMainHdrDtlVo>> responseData = new ResponseData<List<MasterConditionMainHdrDtlVo>>();
        try {
            masterConditionService.insertCondMainDtl(formVo);
            responseData.setData(null);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/update-dtl")
    @ResponseBody
    public ResponseData<List<MasterConditionMainHdrDtlVo>> updateCondMainDtl(@RequestBody MasterConditionMainHdrDtlVo formVo) {
        ResponseData<List<MasterConditionMainHdrDtlVo>> responseData = new ResponseData<List<MasterConditionMainHdrDtlVo>>();
        try {
            masterConditionService.updateCondMainDtl(formVo);
            responseData.setData(null);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/find-hdr")
    @ResponseBody
    public ResponseData<TaMasCondMainHdrForm> findHdr(@RequestBody TaMasCondMainHdrForm formVo) {
        ResponseData<TaMasCondMainHdrForm> responseData = new ResponseData<TaMasCondMainHdrForm>();
        try {
            responseData.setData(masterConditionService.findHdr(formVo));
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/find-hdr-all")
    @ResponseBody
    public ResponseData<List<TaMasCondMainHdrForm>> findHdrAll(@RequestBody TaMasCondMainHdrForm formVo) {
        ResponseData<List<TaMasCondMainHdrForm>> responseData = new ResponseData<List<TaMasCondMainHdrForm>>();
        try {
            responseData.setData(masterConditionService.findHdrAll(formVo));
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/find-dtl")
    @ResponseBody
    public ResponseData<List<TaMasCondMainDtl>> findDtl(@RequestBody TaMasCondMainDtl formVo) {
        ResponseData<List<TaMasCondMainDtl>> responseData = new ResponseData<List<TaMasCondMainDtl>>();
        try {
            responseData.setData(masterConditionService.findDtl(formVo));
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @GetMapping("/find-allhdr")
    @ResponseBody
    public ResponseData<List<TaMasCondMainHdr>> findAllHdr() {
        ResponseData<List<TaMasCondMainHdr>> responseData = new ResponseData<List<TaMasCondMainHdr>>();
        try {
            responseData.setData(masterConditionService.findAllHdr());
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @GetMapping("/condition-message")
    @ResponseBody
    public ResponseData<ConditionMessageVo> conditionMessage() {
        ResponseData<ConditionMessageVo> response = new ResponseData<>();
        try {
            response.setData(this.masterConditionService.conditionMessage());
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @GetMapping("/get-main-cond-range")
    @ResponseBody
    public ResponseData<List<ParamInfo>> getMainCondRange() {
        ResponseData<List<ParamInfo>> response = new ResponseData<>();
        try {
            response.setData(masterConditionService.getMainCondRange());
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @PostMapping("/get-main-cond-hdr")
    @ResponseBody
    public ResponseData<List<TaMasCondMainHdr>> getMainCondHdt(@RequestBody MasCondMainRequestVo formVo) {
        ResponseData<List<TaMasCondMainHdr>> response = new ResponseData<>();
        try {
            response.setData(masterConditionService.getMainCondHdr(formVo));
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

    @PostMapping("/get-main-cond-dtl")
    @ResponseBody
    public ResponseData<List<MasCondMainResponseVo>> getMainCondDtl(@RequestBody MasCondMainRequestVo formVo) {
        ResponseData<List<MasCondMainResponseVo>> response = new ResponseData<>();
        try {
            response.setData(masterConditionService.getMainCondDtl(formVo));
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
    @GetMapping("/get-main-cond-freq-type")
    @ResponseBody
    public ResponseData<List<ParamInfo>> getMainCondFreqType() {
        ResponseData<List<ParamInfo>> response = new ResponseData<>();
        try {
            response.setData(masterConditionService.getMainCondFreqType());
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
//    ============= ta010101 ==============
    @PostMapping("/ta010101-create")
    @ResponseBody
    public ResponseData<String> insertCondMain(@RequestBody Ta010101Vo form) {
        ResponseData<String> response = new ResponseData<String>();
        try {
            response.setData(masterConditionService.insertCondMain(form));
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
    @PostMapping("/ta010101-get")
    @ResponseBody
    public ResponseData<Ta010101Vo> getCondMain(@RequestBody Ta010101Vo form) {
        ResponseData<Ta010101Vo> response = new ResponseData<Ta010101Vo>();
        try {
            response.setData(masterConditionService.getCondMain(form));
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
    
    @GetMapping("/ta010101-get-last-budget-year")
    @ResponseBody
    public ResponseData<Ta010101Vo> getLastBudgetYear() {
    	ResponseData<Ta010101Vo> response = new ResponseData<Ta010101Vo>();
        try {
            response.setData(masterConditionService.getLastBudgetYear());
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

}
