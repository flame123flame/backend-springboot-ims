package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGfdrawAccount;

public interface IaGfdrawAccountRepositoryCustom {
	

	public void batchInsert(List<IaGfdrawAccount> iaGfDrawAccountList) ;
}
