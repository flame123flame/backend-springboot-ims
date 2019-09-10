package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;

public interface IaGfledgerAccountRepositoryCustom {
	public void insertBatch(List<IaGfledgerAccount> iaGfledgerAccountList);
}
