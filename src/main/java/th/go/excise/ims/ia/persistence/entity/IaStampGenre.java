
package th.go.excise.ims.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_STAMP_GENRE")
public class IaStampGenre
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8119473508371056659L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_GENRE_GEN")
    @SequenceGenerator(name = "IA_STAMP_GENRE_GEN", sequenceName = "IA_STAMP_GENRE_SEQ", allocationSize = 1)
    @Column(name = "STAMP_GENRE_ID")
    private Long stampGenreId;
    @Column(name = "STAMP_TYPE_ID")
    private String stampTypeId;
    @Column(name = "STAMP_GENRE_NAME")
    private String stampGenreName;

    public Long getStampGenreId() {
        return stampGenreId;
    }

    public void setStampGenreId(Long stampGenreId) {
        this.stampGenreId = stampGenreId;
    }

    public String getStampTypeId() {
        return stampTypeId;
    }

    public void setStampTypeId(String stampTypeId) {
        this.stampTypeId = stampTypeId;
    }

    public String getStampGenreName() {
        return stampGenreName;
    }

    public void setStampGenreName(String stampGenreName) {
        this.stampGenreName = stampGenreName;
    }

}
