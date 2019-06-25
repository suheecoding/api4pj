package com.kakaopay.api4pj.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.kakaopay.api4pj.mapper.ApiMapper;
import com.kakaopay.api4pj.vo.AccInfo;
import com.kakaopay.api4pj.vo.BranchInfo;
import com.kakaopay.api4pj.vo.TranHis;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;


@Configuration
public class CsvFileRead{
	
	
	private final static Logger logger = LoggerFactory.getLogger(CsvFileRead.class);

	@Autowired
	ApiMapper apiMapper;
	
	@PostConstruct
	private void readCsv() {
		try {
			this.ReadCsv("AccInfo"); 
			this.ReadCsv("BranchInfo");
			this.ReadCsv("TranHis");
		} catch (Exception e) {
			logger.debug("error",e);
		}
	}
	
	
	private List<AccInfo> ReadCsv(String fileName) throws Exception {
		
		List<AccInfo> accInfolist = null;
		List<BranchInfo> branchInfolist = null;
		List<TranHis> tranHislist = null;
		try {
			
			ClassPathResource  file = new ClassPathResource("csv/"+fileName+".csv");
			CSVReader reader = new CSVReader(new FileReader(file.getFile()));
			
			if("AccInfo".equals(fileName)) {
				
				ColumnPositionMappingStrategy<AccInfo> start = new ColumnPositionMappingStrategy<AccInfo>();
				start.setType(AccInfo.class);
				String[] columns = new String[] {"acctNo", "name","brCode"};
				start.setColumnMapping(columns);
				CsvToBean<AccInfo> csv = new CsvToBean<AccInfo>();
				accInfolist = csv.parse(start, reader);
				
				if(accInfolist != null && accInfolist.size() != 0) {
					apiMapper.insertAccInfo(accInfolist);
				}
				
			}else if("BranchInfo".equals(fileName)){
				
				ColumnPositionMappingStrategy<BranchInfo> start = new ColumnPositionMappingStrategy<BranchInfo>();
				start.setType(BranchInfo.class);
				String[] columns = new String[] {"brCode", "brName"};
				start.setColumnMapping(columns);
				CsvToBean<BranchInfo> csv = new CsvToBean<BranchInfo>();
				
				branchInfolist = csv.parse(start, reader);
				apiMapper.insertBranchInfo(branchInfolist);
				
			}else if("TranHis".equals(fileName)) {
				
				ColumnPositionMappingStrategy<TranHis> start = new ColumnPositionMappingStrategy<TranHis>();
				start.setType(TranHis.class);
				String[] columns = new String[] {"trnFldDat","acctNo","trnFldNbr","trnFldAmt","trnFldFee","canFldYon"};
				start.setColumnMapping(columns);
				CsvToBean<TranHis> csv = new CsvToBean<TranHis>();
				tranHislist = csv.parse(start, reader); 
				apiMapper.insertTranHis(tranHislist); 
				
			}
			
		} catch (FileNotFoundException e) {
			logger.debug("error",e);
		}
		
		return accInfolist;
	}
	
}
