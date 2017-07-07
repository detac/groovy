package scripts

println '*** Start ***'

def template = 'Insert into ACCOUNT_TRXS_CC (ID,ACCO_ID,ACCO_PROD_ID,ORUN_ID,BOOKING_NO,BOOKED_AT,VALUE_DATE,AMOUNT,AMOUNT_B_CURR,DEBIT_CREDIT_CD,BOOKING_TYPE,TRANSFERRED,SALARY_PAYMENT,STATUS,UC,DC,REFERENCE_NO,SOURCE_NO,SOURCE_TYPE_CD,VALUE_UNIT,INTRADAY_COUNTER,DISPOSED,BALANCE,DALO_ID,TEXT,ADD_ON1,ADD_ON2,ADD_ON3,ADD_ON4,ADD_ON5,ADD_ON6,Z_FREE_DATE1,Z_FREE_DATE2,Z_FREE_DATE3,Z_FREE_NUMBER1,Z_FREE_NUMBER2,Z_FREE_NUMBER3,Z_FREE_TEXT1,Z_FREE_TEXT2,Z_FREE_TEXT3,Z_FREE_TEXT4,Z_FREE_TEXT5,UM,DM,MTHI_ID,IBF_TEXT_1,IBF_TEXT_2,IBF_CUAT_ID,AMOUNT_CUSTOMER_CURR,AMOUNT_PORTFOLIO_CURR,VALUE_RATE_CURR_ID,IBF_USAGE_1,IBF_USAGE_2,IBF_USAGE_3,IBF_USAGE_4,ALERTER_PROCESSED,BKDOC_KEY,INTERNAL_REF_NO,EXTERNAL_REF_NO,PAYMENT_TYPE_CD,VALUE_BOOK_DATE,VALUE_DATE_BALANCE,EXTL_REF_NO_2,EXTL_REF_NO_3,CARD_ID,ORDE_ID,PAYM_ID,TRANSFERRED_CAMT052,TRANSFERRED_CAMT053,TRANSFERRED_CAMT054,TRANSFERRED_CAMT054_ESR,FEES) \n' +
        'values ($1,528,928,12001,\'$2\',to_date(\'02-OKT-11\',\'DD-MON-RR\'),to_date(\'02-OKT-11\',\'DD-MON-RR\'),6768,6768,\'1\',\'1\',\'0\',\'0\',1,\'HMIDB_LOAD_DATA\',to_date(\'05-FEB-17\',\'DD-MON-RR\'),null,null,null,null,0,\'0\',6768,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,\'1000051501\',to_date(\'06-FEB-17\',\'DD-MON-RR\'),null,\'Einzahlung 3.te Säule\',null,null,6768,6768,797,null,null,null,null,\'0\',null,null,null,null,to_date(\'02-OKT-11\',\'DD-MON-RR\'),6768,null,null,null,null,null,\'0\',\'0\',\'0\',\'0\',null);';

def outFile = '../../resources/out/sqlInserts.sql'

def startId = 3000
def numOfInsertStatements = 1500;

File outputFile = new File(outFile)
if (outputFile.exists()) {
    outputFile.delete()
}

Writer writer = outputFile.newWriter('UTF-8', true)
StringBuilder result = new StringBuilder()

for (def id = startId; id <= startId + numOfInsertStatements; id++) {
    def line = template.replace('$1', id + '')
    line = line.replace('$2', 'Bo-' + id)
    result.append(line + '\n \n' )
}

writer.write(result.toString())
writer.close()