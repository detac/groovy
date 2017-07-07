package scripts

@GrabConfig(systemClassLoader = true)
@Grapes([
        @Grab(
                group = 'com.oracle',
                module = 'ojdbc6',
                version = '11.2.0.4.0-atlassian-hosted'
        )
])

import groovy.sql.Sql

def alias = 'MDB-R183a-LAT'
def sql = Sql.newInstance("jdbc:oracle:thin:@ldap://ldap01.ebs.crealogix.net:389/" + alias + ",cn=Developer,dc=crealogix,dc=net ldap://ldap02.ebs.crealogix.net:389/" + alias + ",cn=Developer,dc=crealogix,dc=net", "HMIDBDBA", "HMIDBDBA", "oracle.jdbc.OracleDriver")

def orun_id = '19901'
def contract_no = '1000021501'

def iso = true;
def dta = false;

if(iso) {
    println 'ISO 20022 - START'
    sql.withTransaction {
        sql.execute "delete from c_payments paym where paym.ispt_id in ( select ispt.id from c_iso_pain_paym_trxs ispt join c_iso_pain_paym_instructions ispi on ispt.ispi_id=ispi.id join c_iso_pain_files isfi on ispi.isfi_id=isfi.id join s_ft_exchange_logs ftel on isfi.filename=ftel.file_name where ftel.type_cd=9 and ftel.uc=" + contract_no + " and ftel.orun_id='" + orun_id + "')"
        sql.execute "delete from c_iso_pain_paym_trxs ispt where ispt.ispi_id in ( select ispi.id from c_iso_pain_paym_instructions ispi join c_iso_pain_files isfi on ispi.isfi_id=isfi.id join s_ft_exchange_logs ftel on isfi.filename=ftel.file_name where ftel.type_cd=9 and ftel.uc=" + contract_no + " and ftel.orun_id='" + orun_id + "')"
        sql.execute "delete from c_iso_pain_paym_instructions ispi where ispi.isfi_id in ( select isfi.id from c_iso_pain_files isfi join s_ft_exchange_logs ftel on isfi.filename=ftel.file_name where ftel.type_cd=9 and ftel.uc=" + contract_no + " and ftel.orun_id='" + orun_id + "')"
        sql.execute "delete from c_iso_pain_files where filename in (select ftel.file_name from s_ft_exchange_logs ftel where ftel.type_cd=9 and ftel.uc=" + contract_no + " and ftel.orun_id='" + orun_id + "')"
        sql.execute "delete from c_download_journal doju where doju.ftel_id in ( select ftel.id from s_ft_exchange_logs ftel where ftel.type_cd=9 and ftel.uc=" + contract_no + " and ftel.orun_id='" + orun_id + "')"
        sql.execute "delete from s_ft_payment_headers ftph where ftph.ftel_id in ( select ftel.id from s_ft_exchange_logs ftel where ftel.type_cd=9 and ftel.uc=" + contract_no + " and ftel.orun_id='" + orun_id + "')"
        sql.execute "delete from s_ft_exchange_logs where type_cd=9 and uc=" + contract_no + " and orun_id='" + orun_id + "'"
    }
    println 'ISO 20022 - END'
}
println ''

if(dta) {
    println 'DTA - START'
    sql.withTransaction {
        sql.execute "delete from c_payments where ftel_id in (select id from s_ft_exchange_logs where login_name=" + contract_no + " and orun_id='" + orun_id + "')"
        sql.execute "delete from c_download_journal where ftel_id in (select id from s_ft_exchange_logs where login_name=" + contract_no + " and orun_id='" + orun_id + "')"
        sql.execute "delete from s_ft_payment_trxs where ftel_id in (select id from s_ft_exchange_logs where login_name=" + contract_no + " and orun_id='" + orun_id + "')"
        sql.execute "delete from s_ft_rejected_rows where ftel_id in (select id from s_ft_exchange_logs where login_name=" + contract_no + " and orun_id='" + orun_id + "')"
        sql.execute "delete from s_ft_payment_headers where ftel_id in (select id from s_ft_exchange_logs where login_name=" + contract_no + " and orun_id='" + orun_id + "')"
        sql.execute "delete from s_ft_exchange_logs where login_name=" + contract_no + " and orun_id='" + orun_id + "'"
    }
    println 'DTA - END'
}
sql.close()