package th.go.excise.ims.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.go.excise.ims.ta.service.TaxOperatorService;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

@RestController
@RequestMapping("/api/ta/tax-operator2")
public class TaxOperator2Controller {

    private static final Logger logger = LoggerFactory.getLogger(TaxOperator2Controller.class);

    @Autowired
    private TaxOperatorService taxOperatorService;

    @PostMapping("/preview-data")
    @ResponseBody
    public ResponseData<TaxOperatorVo> previewData(@RequestBody TaxOperatorFormVo formVo) {
        ResponseData<TaxOperatorVo> response = new ResponseData<>();

        try {
            response.setData(taxOperatorService.getPreviewData(formVo));
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
            response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
        }

        return response;
    }
}
