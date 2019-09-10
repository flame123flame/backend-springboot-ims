package th.go.excise.ims.ia.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaAssetBalance;
import th.go.excise.ims.ia.persistence.repository.IaAssetBalanceRepository;

@Service
public class Int120201Service {
	private Logger logger = LoggerFactory.getLogger(Int120201Service.class);

	@Autowired
	private IaAssetBalanceRepository iaAssetBalanceRepository;

	public IaAssetBalance saveAssetBalance(IaAssetBalance assetBalance) {
		logger.info("saveAssetBalance");

		IaAssetBalance iaAssetBalance = null;
		if (assetBalance.getAssetBalanceId() == null) {
			logger.info("saveAssetBalance == > new");
			iaAssetBalance = iaAssetBalanceRepository.save(assetBalance);
		} else {
			logger.info("saveAssetBalance == > update {}", assetBalance.getAssetBalanceId());
			IaAssetBalance data = iaAssetBalanceRepository.findById(assetBalance.getAssetBalanceId()).get();
			assetBalance.setVersion(data.getVersion());
			iaAssetBalance = iaAssetBalanceRepository.save(assetBalance);
		}

		return iaAssetBalance;
	}
}
