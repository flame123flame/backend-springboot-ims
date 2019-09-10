package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int1503FormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;

public interface ExciseDepaccMasRepositoryCustom {
	
	public List<ExciseDepaccMas> getDepaccMasDropdown();
	
	public void deleteById(Int1503FormVo request);
	
	
}
