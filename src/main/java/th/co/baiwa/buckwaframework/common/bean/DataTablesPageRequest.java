package th.co.baiwa.buckwaframework.common.bean;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Basic Java Bean implementation of {@code Pageable}.
 * This class support pagination from DataTables.
 * 
 * @author Taechapon Himarat (Su)
 */
public class DataTablesPageRequest extends AbstractPageRequest {
	
	private static final long serialVersionUID = 4723435083137473785L;
	
	private final Sort sort;
	
	/**
	 * Creates a new {@link DataTablesPageRequest}.
	 * 
	 * @param start Paging first record indicator (zero-based index - i.e. 0 is the first record).
	 * @param length Number of records that the table can display in the current draw.
	 * @see PageRequest
	 */
	public DataTablesPageRequest(int start, int length) {
		this(start, length, null);
	}
	
	/**
	 * Creates a new {@link DataTablesPageRequest} with sort parameters applied.
	 * 
	 * @param start Paging first record indicator (zero-based index - i.e. 0 is the first record).
	 * @param length Number of records that the table can display in the current draw.
	 * @param direction the direction of the {@link Sort} to be specified, can be {@literal null}.
	 * @param properties the properties to sort by, must not be {@literal null} or empty.
	 */
	public DataTablesPageRequest(int start, int length, Direction direction, String... properties) {
		this(start, length, new Sort(direction, properties));
	}
	
	/**
	 * Creates a new {@link DataTablesPageRequest} with sort parameters applied.
	 * 
	 * @param start Paging first record indicator (zero-based index - i.e. 0 is the first record).
	 * @param length Number of records that the table can display in the current draw.
	 * @param sort can be {@literal null}.
	 */
	public DataTablesPageRequest(int start, int length, Sort sort) {
		super(calculatePage(start, length), length);
		this.sort = sort;
	}
	
	private static int calculatePage(int start, int length) {
		if (start < 0) {
			throw new IllegalArgumentException("Page start must not be less than zero!");
		}
		if (length < 1) {
			throw new IllegalArgumentException("Page length must not be less than one!");
		}
		return start / length;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getSort()
	 */
	@Override
	public Sort getSort() {
		return sort;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#next()
	 */
	@Override
	public Pageable next() {
		return new DataTablesPageRequest(getPageNumber() + 1, getPageSize(), getSort());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.AbstractPageRequest#previous()
	 */
	public DataTablesPageRequest previous() {
		return getPageNumber() == 0 ? this : new DataTablesPageRequest(getPageNumber() - 1, getPageSize(), getSort());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#first()
	 */
	@Override
	public Pageable first() {
		return new DataTablesPageRequest(0, getPageSize(), getSort());
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DataTablesPageRequest)) {
			return false;
		}
		
		DataTablesPageRequest that = (DataTablesPageRequest) obj;
		boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);
		
		return super.equals(that) && sortEqual;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31 * super.hashCode() + (null == sort ? 0 : sort.hashCode());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DataTablesPageRequest [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(),
			sort == null ? null : sort.toString());
	}
	
}
