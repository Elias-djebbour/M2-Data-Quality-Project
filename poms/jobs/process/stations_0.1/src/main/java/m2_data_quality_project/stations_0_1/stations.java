// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package m2_data_quality_project.stations_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: stations Purpose: stations<br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class stations implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "stations";
	private final String projectName = "M2_DATA_QUALITY_PROJECT";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					stations.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(stations.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_11_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_11_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_11_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_11_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_11_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileOutputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileOutputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_3_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_3_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_3_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_10_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_11_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileOutputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileOutputDelimited_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row17Struct implements routines.system.IPersistableComparableLookupRow<row17Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Integer NBS;

		public Integer getNBS() {
			return this.NBS;
		}

		public Integer NBC;

		public Integer getNBC() {
			return this.NBC;
		}

		public Float somme;

		public Float getSomme() {
			return this.somme;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row17Struct other = (row17Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(row17Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.NBS = this.NBS;
			other.NBC = this.NBC;
			other.somme = this.somme;

		}

		public void copyKeysDataTo(row17Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.NBS = readInteger(dis, ois);

				this.NBC = readInteger(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.somme = null;
				} else {
					this.somme = dis.readFloat();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.NBS = readInteger(dis, objectIn);

				this.NBC = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.somme = null;
				} else {
					this.somme = objectIn.readFloat();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.NBS, dos, oos);

				writeInteger(this.NBC, dos, oos);

				if (this.somme == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.somme);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.NBS, dos, objectOut);

				writeInteger(this.NBC, dos, objectOut);

				if (this.somme == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.somme);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",NBS=" + String.valueOf(NBS));
			sb.append(",NBC=" + String.valueOf(NBC));
			sb.append(",somme=" + String.valueOf(somme));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row17Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtAggregateRow_3
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_3> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Integer NBS;

		public Integer getNBS() {
			return this.NBS;
		}

		public Integer NBC;

		public Integer getNBC() {
			return this.NBC;
		}

		public Float somme;

		public Float getSomme() {
			return this.somme;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final OnRowsEndStructtAggregateRow_3 other = (OnRowsEndStructtAggregateRow_3) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtAggregateRow_3 other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.NBS = this.NBS;
			other.NBC = this.NBC;
			other.somme = this.somme;

		}

		public void copyKeysDataTo(OnRowsEndStructtAggregateRow_3 other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					this.NBS = readInteger(dis);

					this.NBC = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.somme = null;
					} else {
						this.somme = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					this.NBS = readInteger(dis);

					this.NBC = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.somme = null;
					} else {
						this.somme = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Integer

				writeInteger(this.NBS, dos);

				// Integer

				writeInteger(this.NBC, dos);

				// Float

				if (this.somme == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.somme);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Integer

				writeInteger(this.NBS, dos);

				// Integer

				writeInteger(this.NBC, dos);

				// Float

				if (this.somme == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.somme);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",NBS=" + String.valueOf(NBS));
			sb.append(",NBC=" + String.valueOf(NBC));
			sb.append(",somme=" + String.valueOf(somme));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_3 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row13Struct other = (row13Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(row13Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.Taux_releve = this.Taux_releve;
			other.Seuil_tolere = this.Seuil_tolere;
			other.Type = this.Type;
			other.ID_Capteur = this.ID_Capteur;
			other.ID_Station = this.ID_Station;

		}

		public void copyKeysDataTo(row13Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row15Struct implements routines.system.IPersistableRow<row15Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row15Struct other = (row15Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(row15Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.Taux_releve = this.Taux_releve;
			other.Seuil_tolere = this.Seuil_tolere;
			other.Type = this.Type;
			other.ID_Capteur = this.ID_Capteur;
			other.ID_Station = this.ID_Station;

		}

		public void copyKeysDataTo(row15Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row15Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row12Struct other = (row12Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.Taux_releve = this.Taux_releve;
			other.Seuil_tolere = this.Seuil_tolere;
			other.Type = this.Type;
			other.ID_Capteur = this.ID_Capteur;
			other.ID_Station = this.ID_Station;

		}

		public void copyKeysDataTo(row12Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_10Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row15Struct row15 = new row15Struct();

				row12Struct row12 = new row12Struct();

				row13Struct row13 = new row13Struct();
				row17Struct row17 = new row17Struct();

				/**
				 * [tAggregateRow_3_AGGOUT begin ] start
				 */

				ok_Hash.put("tAggregateRow_3_AGGOUT", false);
				start_Hash.put("tAggregateRow_3_AGGOUT", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGOUT";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row13");
				}

				int tos_count_tAggregateRow_3_AGGOUT = 0;

// ------------ Seems it is not used

				java.util.Map hashAggreg_tAggregateRow_3 = new java.util.HashMap();

// ------------

				class UtilClass_tAggregateRow_3 { // G_OutBegin_AggR_144

					public double sd(Double[] data) {
						final int n = data.length;
						if (n < 2) {
							return Double.NaN;
						}
						double d1 = 0d;
						double d2 = 0d;

						for (int i = 0; i < data.length; i++) {
							d1 += (data[i] * data[i]);
							d2 += data[i];
						}

						return Math.sqrt((n * d1 - d2 * d2) / n / (n - 1));
					}

					public void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {
						byte r = (byte) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'short/Short'", "'byte/Byte'"));
						}
					}

					public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
						short r = (short) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'int/Integer'", "'short/Short'"));
						}
					}

					public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
						int r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'long/Long'", "'int/Integer'"));
						}
					}

					public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
						long r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'long/Long'"));
						}
					}

					public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							float minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b),
										"'double' or 'BigDecimal'", "'float/Float'"));
							}
						}

						if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
								|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'double' or 'BigDecimal'", "'float/Float'"));
						}
					}

					public void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, short b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					private String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {
						return "Type overflow when adding " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

					private String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {
						return "The double precision is unsufficient to add the value " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

				} // G_OutBegin_AggR_144

				UtilClass_tAggregateRow_3 utilClass_tAggregateRow_3 = new UtilClass_tAggregateRow_3();

				class AggOperationStruct_tAggregateRow_3 { // G_OutBegin_AggR_100

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					Integer ID_Polluant;
					java.util.Date Date;
					int count = 0;
					int NBS_clmCount = 0;
					int NBC_clmCount = 0;
					BigDecimal somme_sum;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

							result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final AggOperationStruct_tAggregateRow_3 other = (AggOperationStruct_tAggregateRow_3) obj;

						if (this.ID_Polluant == null) {
							if (other.ID_Polluant != null)
								return false;
						} else if (!this.ID_Polluant.equals(other.ID_Polluant))
							return false;

						if (this.Date == null) {
							if (other.Date != null)
								return false;
						} else if (!this.Date.equals(other.Date))
							return false;

						return true;
					}

				} // G_OutBegin_AggR_100

				AggOperationStruct_tAggregateRow_3 operation_result_tAggregateRow_3 = null;
				AggOperationStruct_tAggregateRow_3 operation_finder_tAggregateRow_3 = new AggOperationStruct_tAggregateRow_3();
				java.util.Map<AggOperationStruct_tAggregateRow_3, AggOperationStruct_tAggregateRow_3> hash_tAggregateRow_3 = new java.util.HashMap<AggOperationStruct_tAggregateRow_3, AggOperationStruct_tAggregateRow_3>();

				/**
				 * [tAggregateRow_3_AGGOUT begin ] stop
				 */

				/**
				 * [tUnite_4 begin ] start
				 */

				ok_Hash.put("tUnite_4", false);
				start_Hash.put("tUnite_4", System.currentTimeMillis());

				currentComponent = "tUnite_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12", "row15");
				}

				int tos_count_tUnite_4 = 0;

				int nb_line_tUnite_4 = 0;

				/**
				 * [tUnite_4 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_10 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_10", false);
				start_Hash.put("tFileInputDelimited_10", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_10";

				int tos_count_tFileInputDelimited_10 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_10 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_10 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_10 = null;
				int limit_tFileInputDelimited_10 = -1;
				try {

					Object filename_tFileInputDelimited_10 = "C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out2.csv";
					if (filename_tFileInputDelimited_10 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_10 = 0, random_value_tFileInputDelimited_10 = -1;
						if (footer_value_tFileInputDelimited_10 > 0 || random_value_tFileInputDelimited_10 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_10 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out2.csv",
								"ISO-8859-15", ",", "\n", true, 1, 0, limit_tFileInputDelimited_10, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_10 != null && fid_tFileInputDelimited_10.nextRecord()) {
						rowstate_tFileInputDelimited_10.reset();

						row15 = null;

						boolean whetherReject_tFileInputDelimited_10 = false;
						row15 = new row15Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_10 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_10 = 0;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row15.ID_Polluant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Polluant", "row15", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row15.ID_Polluant = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 1;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row15.Date = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd");

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Date", "row15", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row15.Date = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 2;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row15.Taux_releve = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Taux_releve", "row15", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row15.Taux_releve = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 3;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row15.Seuil_tolere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Seuil_tolere", "row15", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row15.Seuil_tolere = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 4;

							row15.Type = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);

							columnIndexWithD_tFileInputDelimited_10 = 5;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row15.ID_Capteur = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Capteur", "row15", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row15.ID_Capteur = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 6;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row15.ID_Station = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Station", "row15", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row15.ID_Station = null;

							}

							if (rowstate_tFileInputDelimited_10.getException() != null) {
								throw rowstate_tFileInputDelimited_10.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_10 = true;

							System.err.println(e.getMessage());
							row15 = null;

						}

						/**
						 * [tFileInputDelimited_10 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_10 main ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						tos_count_tFileInputDelimited_10++;

						/**
						 * [tFileInputDelimited_10 main ] stop
						 */

						/**
						 * [tFileInputDelimited_10 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						/**
						 * [tFileInputDelimited_10 process_data_begin ] stop
						 */
// Start of branch "row15"
						if (row15 != null) {

							/**
							 * [tUnite_4 main ] start
							 */

							currentComponent = "tUnite_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row15"

								);
							}

//////////

// for output
							row13 = new row13Struct();

							row13.ID_Polluant = row15.ID_Polluant;
							row13.Date = row15.Date;
							row13.Taux_releve = row15.Taux_releve;
							row13.Seuil_tolere = row15.Seuil_tolere;
							row13.Type = row15.Type;
							row13.ID_Capteur = row15.ID_Capteur;
							row13.ID_Station = row15.ID_Station;

							nb_line_tUnite_4++;

//////////

							tos_count_tUnite_4++;

							/**
							 * [tUnite_4 main ] stop
							 */

							/**
							 * [tUnite_4 process_data_begin ] start
							 */

							currentComponent = "tUnite_4";

							/**
							 * [tUnite_4 process_data_begin ] stop
							 */

							/**
							 * [tAggregateRow_3_AGGOUT main ] start
							 */

							currentVirtualComponent = "tAggregateRow_3";

							currentComponent = "tAggregateRow_3_AGGOUT";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row13"

								);
							}

							operation_finder_tAggregateRow_3.ID_Polluant = row13.ID_Polluant;
							operation_finder_tAggregateRow_3.Date = row13.Date;

							operation_finder_tAggregateRow_3.hashCodeDirty = true;

							operation_result_tAggregateRow_3 = hash_tAggregateRow_3
									.get(operation_finder_tAggregateRow_3);

							if (operation_result_tAggregateRow_3 == null) { // G_OutMain_AggR_001

								operation_result_tAggregateRow_3 = new AggOperationStruct_tAggregateRow_3();

								operation_result_tAggregateRow_3.ID_Polluant = operation_finder_tAggregateRow_3.ID_Polluant;
								operation_result_tAggregateRow_3.Date = operation_finder_tAggregateRow_3.Date;

								hash_tAggregateRow_3.put(operation_result_tAggregateRow_3,
										operation_result_tAggregateRow_3);

							} // G_OutMain_AggR_001

							if (row13.ID_Station != null) { // G_OutMain_AggR_546

								operation_result_tAggregateRow_3.NBS_clmCount++;
								operation_result_tAggregateRow_3.count++;

							} // G_OutMain_AggR_546

							if (row13.ID_Capteur != null) { // G_OutMain_AggR_546

								operation_result_tAggregateRow_3.NBC_clmCount++;
							} // G_OutMain_AggR_546

							if (row13.Taux_releve != null) { // G_OutMain_AggR_546

								if (operation_result_tAggregateRow_3.somme_sum == null) {
									operation_result_tAggregateRow_3.somme_sum = new BigDecimal(0);
								}
								operation_result_tAggregateRow_3.somme_sum = operation_result_tAggregateRow_3.somme_sum
										.add(new BigDecimal(String.valueOf(row13.Taux_releve)));

							} // G_OutMain_AggR_546

							tos_count_tAggregateRow_3_AGGOUT++;

							/**
							 * [tAggregateRow_3_AGGOUT main ] stop
							 */

							/**
							 * [tAggregateRow_3_AGGOUT process_data_begin ] start
							 */

							currentVirtualComponent = "tAggregateRow_3";

							currentComponent = "tAggregateRow_3_AGGOUT";

							/**
							 * [tAggregateRow_3_AGGOUT process_data_begin ] stop
							 */

							/**
							 * [tAggregateRow_3_AGGOUT process_data_end ] start
							 */

							currentVirtualComponent = "tAggregateRow_3";

							currentComponent = "tAggregateRow_3_AGGOUT";

							/**
							 * [tAggregateRow_3_AGGOUT process_data_end ] stop
							 */

							/**
							 * [tUnite_4 process_data_end ] start
							 */

							currentComponent = "tUnite_4";

							/**
							 * [tUnite_4 process_data_end ] stop
							 */

						} // End of branch "row15"

						/**
						 * [tFileInputDelimited_10 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						/**
						 * [tFileInputDelimited_10 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_10 end ] start
						 */

						currentComponent = "tFileInputDelimited_10";

					}
				} finally {
					if (!((Object) ("C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out2.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_10 != null) {
							fid_tFileInputDelimited_10.close();
						}
					}
					if (fid_tFileInputDelimited_10 != null) {
						globalMap.put("tFileInputDelimited_10_NB_LINE", fid_tFileInputDelimited_10.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_10", true);
				end_Hash.put("tFileInputDelimited_10", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_10 end ] stop
				 */

				/**
				 * [tFileInputDelimited_9 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_9", false);
				start_Hash.put("tFileInputDelimited_9", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_9";

				int tos_count_tFileInputDelimited_9 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_9 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_9 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_9 = null;
				int limit_tFileInputDelimited_9 = -1;
				try {

					Object filename_tFileInputDelimited_9 = "C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv";
					if (filename_tFileInputDelimited_9 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_9 = 0, random_value_tFileInputDelimited_9 = -1;
						if (footer_value_tFileInputDelimited_9 > 0 || random_value_tFileInputDelimited_9 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_9 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv",
								"ISO-8859-15", ",", "\n", true, 1, 0, limit_tFileInputDelimited_9, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_9 != null && fid_tFileInputDelimited_9.nextRecord()) {
						rowstate_tFileInputDelimited_9.reset();

						row12 = null;

						boolean whetherReject_tFileInputDelimited_9 = false;
						row12 = new row12Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_9 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_9 = 0;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row12.ID_Polluant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Polluant", "row12", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row12.ID_Polluant = null;

							}

							columnIndexWithD_tFileInputDelimited_9 = 1;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row12.Date = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd");

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Date", "row12", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row12.Date = null;

							}

							columnIndexWithD_tFileInputDelimited_9 = 2;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row12.Taux_releve = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Taux_releve", "row12", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row12.Taux_releve = null;

							}

							columnIndexWithD_tFileInputDelimited_9 = 3;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row12.Seuil_tolere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Seuil_tolere", "row12", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row12.Seuil_tolere = null;

							}

							columnIndexWithD_tFileInputDelimited_9 = 4;

							row12.Type = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 5;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row12.ID_Capteur = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Capteur", "row12", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row12.ID_Capteur = null;

							}

							columnIndexWithD_tFileInputDelimited_9 = 6;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row12.ID_Station = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Station", "row12", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row12.ID_Station = null;

							}

							if (rowstate_tFileInputDelimited_9.getException() != null) {
								throw rowstate_tFileInputDelimited_9.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_9 = true;

							System.err.println(e.getMessage());
							row12 = null;

						}

						/**
						 * [tFileInputDelimited_9 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_9 main ] start
						 */

						currentComponent = "tFileInputDelimited_9";

						tos_count_tFileInputDelimited_9++;

						/**
						 * [tFileInputDelimited_9 main ] stop
						 */

						/**
						 * [tFileInputDelimited_9 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_9";

						/**
						 * [tFileInputDelimited_9 process_data_begin ] stop
						 */
// Start of branch "row12"
						if (row12 != null) {

							/**
							 * [tUnite_4 main ] start
							 */

							currentComponent = "tUnite_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row12"

								);
							}

//////////

// for output
							row13 = new row13Struct();

							row13.ID_Polluant = row12.ID_Polluant;
							row13.Date = row12.Date;
							row13.Taux_releve = row12.Taux_releve;
							row13.Seuil_tolere = row12.Seuil_tolere;
							row13.Type = row12.Type;
							row13.ID_Capteur = row12.ID_Capteur;
							row13.ID_Station = row12.ID_Station;

							nb_line_tUnite_4++;

//////////

							tos_count_tUnite_4++;

							/**
							 * [tUnite_4 main ] stop
							 */

							/**
							 * [tUnite_4 process_data_begin ] start
							 */

							currentComponent = "tUnite_4";

							/**
							 * [tUnite_4 process_data_begin ] stop
							 */

							/**
							 * [tAggregateRow_3_AGGOUT main ] start
							 */

							currentVirtualComponent = "tAggregateRow_3";

							currentComponent = "tAggregateRow_3_AGGOUT";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row13"

								);
							}

							operation_finder_tAggregateRow_3.ID_Polluant = row13.ID_Polluant;
							operation_finder_tAggregateRow_3.Date = row13.Date;

							operation_finder_tAggregateRow_3.hashCodeDirty = true;

							operation_result_tAggregateRow_3 = hash_tAggregateRow_3
									.get(operation_finder_tAggregateRow_3);

							if (operation_result_tAggregateRow_3 == null) { // G_OutMain_AggR_001

								operation_result_tAggregateRow_3 = new AggOperationStruct_tAggregateRow_3();

								operation_result_tAggregateRow_3.ID_Polluant = operation_finder_tAggregateRow_3.ID_Polluant;
								operation_result_tAggregateRow_3.Date = operation_finder_tAggregateRow_3.Date;

								hash_tAggregateRow_3.put(operation_result_tAggregateRow_3,
										operation_result_tAggregateRow_3);

							} // G_OutMain_AggR_001

							if (row13.ID_Station != null) { // G_OutMain_AggR_546

								operation_result_tAggregateRow_3.NBS_clmCount++;
								operation_result_tAggregateRow_3.count++;

							} // G_OutMain_AggR_546

							if (row13.ID_Capteur != null) { // G_OutMain_AggR_546

								operation_result_tAggregateRow_3.NBC_clmCount++;
							} // G_OutMain_AggR_546

							if (row13.Taux_releve != null) { // G_OutMain_AggR_546

								if (operation_result_tAggregateRow_3.somme_sum == null) {
									operation_result_tAggregateRow_3.somme_sum = new BigDecimal(0);
								}
								operation_result_tAggregateRow_3.somme_sum = operation_result_tAggregateRow_3.somme_sum
										.add(new BigDecimal(String.valueOf(row13.Taux_releve)));

							} // G_OutMain_AggR_546

							tos_count_tAggregateRow_3_AGGOUT++;

							/**
							 * [tAggregateRow_3_AGGOUT main ] stop
							 */

							/**
							 * [tAggregateRow_3_AGGOUT process_data_begin ] start
							 */

							currentVirtualComponent = "tAggregateRow_3";

							currentComponent = "tAggregateRow_3_AGGOUT";

							/**
							 * [tAggregateRow_3_AGGOUT process_data_begin ] stop
							 */

							/**
							 * [tAggregateRow_3_AGGOUT process_data_end ] start
							 */

							currentVirtualComponent = "tAggregateRow_3";

							currentComponent = "tAggregateRow_3_AGGOUT";

							/**
							 * [tAggregateRow_3_AGGOUT process_data_end ] stop
							 */

							/**
							 * [tUnite_4 process_data_end ] start
							 */

							currentComponent = "tUnite_4";

							/**
							 * [tUnite_4 process_data_end ] stop
							 */

						} // End of branch "row12"

						/**
						 * [tFileInputDelimited_9 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_9";

						/**
						 * [tFileInputDelimited_9 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_9 end ] start
						 */

						currentComponent = "tFileInputDelimited_9";

					}
				} finally {
					if (!((Object) ("C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_9 != null) {
							fid_tFileInputDelimited_9.close();
						}
					}
					if (fid_tFileInputDelimited_9 != null) {
						globalMap.put("tFileInputDelimited_9_NB_LINE", fid_tFileInputDelimited_9.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_9", true);
				end_Hash.put("tFileInputDelimited_9", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_9 end ] stop
				 */

				/**
				 * [tUnite_4 end ] start
				 */

				currentComponent = "tUnite_4";

				globalMap.put("tUnite_4_NB_LINE", nb_line_tUnite_4);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12", "row15");
				}

				ok_Hash.put("tUnite_4", true);
				end_Hash.put("tUnite_4", System.currentTimeMillis());

				/**
				 * [tUnite_4 end ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGOUT";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row13");
				}

				ok_Hash.put("tAggregateRow_3_AGGOUT", true);
				end_Hash.put("tAggregateRow_3_AGGOUT", System.currentTimeMillis());

				/**
				 * [tAggregateRow_3_AGGOUT end ] stop
				 */

				/**
				 * [tAdvancedHash_row17 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row17", false);
				start_Hash.put("tAdvancedHash_row17", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row17";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row17");
				}

				int tos_count_tAdvancedHash_row17 = 0;

				// connection name:row17
				// source node:tAggregateRow_3_AGGIN - inputs:(OnRowsEnd) outputs:(row17,row17)
				// | target node:tAdvancedHash_row17 - inputs:(row17) outputs:()
				// linked node: tMap_5 - inputs:(row17,row16) outputs:(out3)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row17 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct> tHash_Lookup_row17 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row17Struct>getLookup(matchingModeEnum_row17);

				globalMap.put("tHash_Lookup_row17", tHash_Lookup_row17);

				/**
				 * [tAdvancedHash_row17 begin ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGIN begin ] start
				 */

				ok_Hash.put("tAggregateRow_3_AGGIN", false);
				start_Hash.put("tAggregateRow_3_AGGIN", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGIN";

				int tos_count_tAggregateRow_3_AGGIN = 0;

				java.util.Collection<AggOperationStruct_tAggregateRow_3> values_tAggregateRow_3 = hash_tAggregateRow_3
						.values();

				globalMap.put("tAggregateRow_3_NB_LINE", values_tAggregateRow_3.size());

				for (AggOperationStruct_tAggregateRow_3 aggregated_row_tAggregateRow_3 : values_tAggregateRow_3) { // G_AggR_600

					/**
					 * [tAggregateRow_3_AGGIN begin ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN main ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

					row17.ID_Polluant = aggregated_row_tAggregateRow_3.ID_Polluant;

					row17.Date = aggregated_row_tAggregateRow_3.Date;
					row17.NBS = (int) aggregated_row_tAggregateRow_3.count;
					row17.NBS = (int) aggregated_row_tAggregateRow_3.NBS_clmCount;
					row17.NBC = (int) aggregated_row_tAggregateRow_3.count;
					row17.NBC = (int) aggregated_row_tAggregateRow_3.NBC_clmCount;

					if (aggregated_row_tAggregateRow_3.somme_sum != null) {
						row17.somme = aggregated_row_tAggregateRow_3.somme_sum.floatValue();

					} else {

						row17.somme = null;

					}

					tos_count_tAggregateRow_3_AGGIN++;

					/**
					 * [tAggregateRow_3_AGGIN main ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

					/**
					 * [tAggregateRow_3_AGGIN process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row17 main ] start
					 */

					currentComponent = "tAdvancedHash_row17";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row17"

						);
					}

					row17Struct row17_HashRow = new row17Struct();

					row17_HashRow.ID_Polluant = row17.ID_Polluant;

					row17_HashRow.Date = row17.Date;

					row17_HashRow.NBS = row17.NBS;

					row17_HashRow.NBC = row17.NBC;

					row17_HashRow.somme = row17.somme;

					tHash_Lookup_row17.put(row17_HashRow);

					tos_count_tAdvancedHash_row17++;

					/**
					 * [tAdvancedHash_row17 main ] stop
					 */

					/**
					 * [tAdvancedHash_row17 process_data_begin ] start
					 */

					currentComponent = "tAdvancedHash_row17";

					/**
					 * [tAdvancedHash_row17 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row17 process_data_end ] start
					 */

					currentComponent = "tAdvancedHash_row17";

					/**
					 * [tAdvancedHash_row17 process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

					/**
					 * [tAggregateRow_3_AGGIN process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN end ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

				} // G_AggR_600

				ok_Hash.put("tAggregateRow_3_AGGIN", true);
				end_Hash.put("tAggregateRow_3_AGGIN", System.currentTimeMillis());

				/**
				 * [tAggregateRow_3_AGGIN end ] stop
				 */

				/**
				 * [tAdvancedHash_row17 end ] start
				 */

				currentComponent = "tAdvancedHash_row17";

				tHash_Lookup_row17.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row17");
				}

				ok_Hash.put("tAdvancedHash_row17", true);
				end_Hash.put("tAdvancedHash_row17", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row17 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tAggregateRow_3_AGGIN"
			globalMap.remove("tAggregateRow_3");

			try {

				/**
				 * [tFileInputDelimited_10 finally ] start
				 */

				currentComponent = "tFileInputDelimited_10";

				/**
				 * [tFileInputDelimited_10 finally ] stop
				 */

				/**
				 * [tFileInputDelimited_9 finally ] start
				 */

				currentComponent = "tFileInputDelimited_9";

				/**
				 * [tFileInputDelimited_9 finally ] stop
				 */

				/**
				 * [tUnite_4 finally ] start
				 */

				currentComponent = "tUnite_4";

				/**
				 * [tUnite_4 finally ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGOUT finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGOUT";

				/**
				 * [tAggregateRow_3_AGGOUT finally ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGIN finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGIN";

				/**
				 * [tAggregateRow_3_AGGIN finally ] stop
				 */

				/**
				 * [tAdvancedHash_row17 finally ] start
				 */

				currentComponent = "tAdvancedHash_row17";

				/**
				 * [tAdvancedHash_row17 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", 1);
	}

	public static class out3Struct implements routines.system.IPersistableRow<out3Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public float Taux_Moyen_Jour;

		public float getTaux_Moyen_Jour() {
			return this.Taux_Moyen_Jour;
		}

		public Integer NBS;

		public Integer getNBS() {
			return this.NBS;
		}

		public Integer NBC;

		public Integer getNBC() {
			return this.NBC;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final out3Struct other = (out3Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			return true;
		}

		public void copyDataTo(out3Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Taux_Moyen_Jour = this.Taux_Moyen_Jour;
			other.NBS = this.NBS;
			other.NBC = this.NBC;
			other.Statut = this.Statut;

		}

		public void copyKeysDataTo(out3Struct other) {

			other.ID_Polluant = this.ID_Polluant;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Taux_Moyen_Jour = dis.readFloat();

					this.NBS = readInteger(dis);

					this.NBC = readInteger(dis);

					this.Statut = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Taux_Moyen_Jour = dis.readFloat();

					this.NBS = readInteger(dis);

					this.NBC = readInteger(dis);

					this.Statut = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// float

				dos.writeFloat(this.Taux_Moyen_Jour);

				// Integer

				writeInteger(this.NBS, dos);

				// Integer

				writeInteger(this.NBC, dos);

				// String

				writeString(this.Statut, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// float

				dos.writeFloat(this.Taux_Moyen_Jour);

				// Integer

				writeInteger(this.NBS, dos);

				// Integer

				writeInteger(this.NBC, dos);

				// String

				writeString(this.Statut, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Taux_Moyen_Jour=" + String.valueOf(Taux_Moyen_Jour));
			sb.append(",NBS=" + String.valueOf(NBS));
			sb.append(",NBC=" + String.valueOf(NBC));
			sb.append(",Statut=" + Statut);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row16Struct implements routines.system.IPersistableRow<row16Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row16Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row14Struct implements routines.system.IPersistableRow<row14Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row14Struct other = (row14Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(row14Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.Taux_releve = this.Taux_releve;
			other.Seuil_tolere = this.Seuil_tolere;
			other.Type = this.Type;
			other.ID_Capteur = this.ID_Capteur;
			other.ID_Station = this.ID_Station;

		}

		public void copyKeysDataTo(row14Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row14Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_11Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_11Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tFileInputDelimited_11Struct other = (after_tFileInputDelimited_11Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(after_tFileInputDelimited_11Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.Taux_releve = this.Taux_releve;
			other.Seuil_tolere = this.Seuil_tolere;
			other.Type = this.Type;
			other.ID_Capteur = this.ID_Capteur;
			other.ID_Station = this.ID_Station;

		}

		public void copyKeysDataTo(after_tFileInputDelimited_11Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_11Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row18Struct implements routines.system.IPersistableRow<row18Struct> {
		final static byte[] commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		static byte[] commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_Polluant;

		public Integer getID_Polluant() {
			return this.ID_Polluant;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Float Taux_releve;

		public Float getTaux_releve() {
			return this.Taux_releve;
		}

		public Integer Seuil_tolere;

		public Integer getSeuil_tolere() {
			return this.Seuil_tolere;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public Integer ID_Capteur;

		public Integer getID_Capteur() {
			return this.ID_Capteur;
		}

		public Integer ID_Station;

		public Integer getID_Station() {
			return this.ID_Station;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_Polluant == null) ? 0 : this.ID_Polluant.hashCode());

				result = prime * result + ((this.Date == null) ? 0 : this.Date.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row18Struct other = (row18Struct) obj;

			if (this.ID_Polluant == null) {
				if (other.ID_Polluant != null)
					return false;

			} else if (!this.ID_Polluant.equals(other.ID_Polluant))

				return false;

			if (this.Date == null) {
				if (other.Date != null)
					return false;

			} else if (!this.Date.equals(other.Date))

				return false;

			return true;
		}

		public void copyDataTo(row18Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;
			other.Taux_releve = this.Taux_releve;
			other.Seuil_tolere = this.Seuil_tolere;
			other.Type = this.Type;
			other.ID_Capteur = this.ID_Capteur;
			other.ID_Station = this.ID_Station;

		}

		public void copyKeysDataTo(row18Struct other) {

			other.ID_Polluant = this.ID_Polluant;
			other.Date = this.Date;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length) {
					if (length < 1024 && commonByteArray_M2_DATA_QUALITY_PROJECT_stations.length == 0) {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[1024];
					} else {
						commonByteArray_M2_DATA_QUALITY_PROJECT_stations = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length);
				strReturn = new String(commonByteArray_M2_DATA_QUALITY_PROJECT_stations, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_M2_DATA_QUALITY_PROJECT_stations) {

				try {

					int length = 0;

					this.ID_Polluant = readInteger(dis);

					this.Date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Taux_releve = null;
					} else {
						this.Taux_releve = dis.readFloat();
					}

					this.Seuil_tolere = readInteger(dis);

					this.Type = readString(dis);

					this.ID_Capteur = readInteger(dis);

					this.ID_Station = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_Polluant, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// Float

				if (this.Taux_releve == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Taux_releve);
				}

				// Integer

				writeInteger(this.Seuil_tolere, dos);

				// String

				writeString(this.Type, dos);

				// Integer

				writeInteger(this.ID_Capteur, dos);

				// Integer

				writeInteger(this.ID_Station, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Polluant=" + String.valueOf(ID_Polluant));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Taux_releve=" + String.valueOf(Taux_releve));
			sb.append(",Seuil_tolere=" + String.valueOf(Seuil_tolere));
			sb.append(",Type=" + Type);
			sb.append(",ID_Capteur=" + String.valueOf(ID_Capteur));
			sb.append(",ID_Station=" + String.valueOf(ID_Station));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row18Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Polluant, other.ID_Polluant);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date, other.Date);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_11Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_11_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_10Process(globalMap);

				row14Struct row14 = new row14Struct();

				row18Struct row18 = new row18Struct();

				row16Struct row16 = new row16Struct();
				out3Struct out3 = new out3Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out3");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[5];

					public void addRow(String[] row) {

						for (int i = 0; i < 5; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 4 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 4 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[4] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("Paris OUIIII");
				util_tLogRow_1.addRow(new String[] { "ID_Polluant", "Taux_Moyen_Jour", "NBS", "NBC", "Statut", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row16");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct> tHash_Lookup_row17 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct>) globalMap
						.get("tHash_Lookup_row17"));

				row17Struct row17HashKey = new row17Struct();
				row17Struct row17Default = new row17Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
					float var1;
					String var2;
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out3Struct out3_tmp = new out3Struct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tUnite_5 begin ] start
				 */

				ok_Hash.put("tUnite_5", false);
				start_Hash.put("tUnite_5", System.currentTimeMillis());

				currentComponent = "tUnite_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row18", "row14");
				}

				int tos_count_tUnite_5 = 0;

				int nb_line_tUnite_5 = 0;

				/**
				 * [tUnite_5 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_11 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_11", false);
				start_Hash.put("tFileInputDelimited_11", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_11";

				int tos_count_tFileInputDelimited_11 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_11 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_11 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_11 = null;
				int limit_tFileInputDelimited_11 = -1;
				try {

					Object filename_tFileInputDelimited_11 = "C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out2.csv";
					if (filename_tFileInputDelimited_11 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_11 = 0, random_value_tFileInputDelimited_11 = -1;
						if (footer_value_tFileInputDelimited_11 > 0 || random_value_tFileInputDelimited_11 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_11 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out2.csv",
								"ISO-8859-15", ",", "\n", true, 1, 0, limit_tFileInputDelimited_11, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_11 != null && fid_tFileInputDelimited_11.nextRecord()) {
						rowstate_tFileInputDelimited_11.reset();

						row14 = null;

						boolean whetherReject_tFileInputDelimited_11 = false;
						row14 = new row14Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_11 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_11 = 0;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row14.ID_Polluant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Polluant", "row14", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row14.ID_Polluant = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 1;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row14.Date = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd");

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Date", "row14", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row14.Date = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 2;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row14.Taux_releve = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Taux_releve", "row14", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row14.Taux_releve = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 3;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row14.Seuil_tolere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Seuil_tolere", "row14", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row14.Seuil_tolere = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 4;

							row14.Type = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);

							columnIndexWithD_tFileInputDelimited_11 = 5;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row14.ID_Capteur = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Capteur", "row14", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row14.ID_Capteur = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 6;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row14.ID_Station = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Station", "row14", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row14.ID_Station = null;

							}

							if (rowstate_tFileInputDelimited_11.getException() != null) {
								throw rowstate_tFileInputDelimited_11.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_11 = true;

							System.err.println(e.getMessage());
							row14 = null;

						}

						/**
						 * [tFileInputDelimited_11 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_11 main ] start
						 */

						currentComponent = "tFileInputDelimited_11";

						tos_count_tFileInputDelimited_11++;

						/**
						 * [tFileInputDelimited_11 main ] stop
						 */

						/**
						 * [tFileInputDelimited_11 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_11";

						/**
						 * [tFileInputDelimited_11 process_data_begin ] stop
						 */
// Start of branch "row14"
						if (row14 != null) {

							/**
							 * [tUnite_5 main ] start
							 */

							currentComponent = "tUnite_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row14"

								);
							}

//////////

// for output
							row16 = new row16Struct();

							row16.ID_Polluant = row14.ID_Polluant;
							row16.Date = row14.Date;
							row16.Taux_releve = row14.Taux_releve;
							row16.Seuil_tolere = row14.Seuil_tolere;
							row16.Type = row14.Type;
							row16.ID_Capteur = row14.ID_Capteur;
							row16.ID_Station = row14.ID_Station;

							nb_line_tUnite_5++;

//////////

							tos_count_tUnite_5++;

							/**
							 * [tUnite_5 main ] stop
							 */

							/**
							 * [tUnite_5 process_data_begin ] start
							 */

							currentComponent = "tUnite_5";

							/**
							 * [tUnite_5 process_data_begin ] stop
							 */

							/**
							 * [tMap_5 main ] start
							 */

							currentComponent = "tMap_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row16"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_5 = false;
							boolean mainRowRejected_tMap_5 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row17"
							///////////////////////////////////////////////

							boolean forceLooprow17 = false;

							row17Struct row17ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_5 = false;

								row17HashKey.ID_Polluant = row16.ID_Polluant;

								row17HashKey.Date = row16.Date == null ? null
										: new java.util.Date(row16.Date.getTime());

								row17HashKey.hashCodeDirty = true;

								tHash_Lookup_row17.lookup(row17HashKey);

								if (!tHash_Lookup_row17.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_5 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row17 != null && tHash_Lookup_row17.getCount(row17HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
								// 'row17' and it contains more one result from keys : row17.ID_Polluant = '" +
								// row17HashKey.ID_Polluant + "', row17.Date = '" + row17HashKey.Date + "'");
							} // G 071

							row17Struct row17 = null;

							row17Struct fromLookup_row17 = null;
							row17 = row17Default;

							if (tHash_Lookup_row17 != null && tHash_Lookup_row17.hasNext()) { // G 099

								fromLookup_row17 = tHash_Lookup_row17.next();

							} // G 099

							if (fromLookup_row17 != null) {
								row17 = fromLookup_row17;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_5__Struct Var = Var__tMap_5;
								Var.var1 = (row17.somme / (row17.NBS + row17.NBC));
								Var.var2 = Var.var1 > row16.Seuil_tolere ? "Alerte Pollution" : "Normal";// ###############################
								// ###############################
								// # Output tables

								out3 = null;

								if (!rejectedInnerJoin_tMap_5) {

// # Output table : 'out3'
									out3_tmp.ID_Polluant = row16.ID_Polluant;
									out3_tmp.Taux_Moyen_Jour = Var.var1;
									out3_tmp.NBS = row17.NBS;
									out3_tmp.NBC = row17.NBC;
									out3_tmp.Statut = Var.var2;
									out3 = out3_tmp;
								} // closing inner join bracket (2)
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_5 = false;

							tos_count_tMap_5++;

							/**
							 * [tMap_5 main ] stop
							 */

							/**
							 * [tMap_5 process_data_begin ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_begin ] stop
							 */
// Start of branch "out3"
							if (out3 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out3"

									);
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[5];

								if (out3.ID_Polluant != null) { //
									row_tLogRow_1[0] = String.valueOf(out3.ID_Polluant);

								} //

								row_tLogRow_1[1] = FormatterUtils.formatUnwithE(out3.Taux_Moyen_Jour);

								if (out3.NBS != null) { //
									row_tLogRow_1[2] = String.valueOf(out3.NBS);

								} //

								if (out3.NBC != null) { //
									row_tLogRow_1[3] = String.valueOf(out3.NBC);

								} //

								if (out3.Statut != null) { //
									row_tLogRow_1[4] = String.valueOf(out3.Statut);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "out3"

							/**
							 * [tMap_5 process_data_end ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_end ] stop
							 */

							/**
							 * [tUnite_5 process_data_end ] start
							 */

							currentComponent = "tUnite_5";

							/**
							 * [tUnite_5 process_data_end ] stop
							 */

						} // End of branch "row14"

						/**
						 * [tFileInputDelimited_11 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_11";

						/**
						 * [tFileInputDelimited_11 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_11 end ] start
						 */

						currentComponent = "tFileInputDelimited_11";

					}
				} finally {
					if (!((Object) ("C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out2.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_11 != null) {
							fid_tFileInputDelimited_11.close();
						}
					}
					if (fid_tFileInputDelimited_11 != null) {
						globalMap.put("tFileInputDelimited_11_NB_LINE", fid_tFileInputDelimited_11.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_11", true);
				end_Hash.put("tFileInputDelimited_11", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_11 end ] stop
				 */

				/**
				 * [tFileInputDelimited_12 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_12", false);
				start_Hash.put("tFileInputDelimited_12", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_12";

				int tos_count_tFileInputDelimited_12 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_12 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_12 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_12 = null;
				int limit_tFileInputDelimited_12 = -1;
				try {

					Object filename_tFileInputDelimited_12 = "C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv";
					if (filename_tFileInputDelimited_12 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_12 = 0, random_value_tFileInputDelimited_12 = -1;
						if (footer_value_tFileInputDelimited_12 > 0 || random_value_tFileInputDelimited_12 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_12 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv",
								"ISO-8859-15", ",", "\n", true, 1, 0, limit_tFileInputDelimited_12, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_12 != null && fid_tFileInputDelimited_12.nextRecord()) {
						rowstate_tFileInputDelimited_12.reset();

						row18 = null;

						boolean whetherReject_tFileInputDelimited_12 = false;
						row18 = new row18Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_12 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_12 = 0;

							temp = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);
							if (temp.length() > 0) {

								try {

									row18.ID_Polluant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_12) {
									globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE",
											ex_tFileInputDelimited_12.getMessage());
									rowstate_tFileInputDelimited_12.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Polluant", "row18", temp, ex_tFileInputDelimited_12),
											ex_tFileInputDelimited_12));
								}

							} else {

								row18.ID_Polluant = null;

							}

							columnIndexWithD_tFileInputDelimited_12 = 1;

							temp = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);
							if (temp.length() > 0) {

								try {

									row18.Date = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd");

								} catch (java.lang.Exception ex_tFileInputDelimited_12) {
									globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE",
											ex_tFileInputDelimited_12.getMessage());
									rowstate_tFileInputDelimited_12.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Date", "row18", temp, ex_tFileInputDelimited_12),
											ex_tFileInputDelimited_12));
								}

							} else {

								row18.Date = null;

							}

							columnIndexWithD_tFileInputDelimited_12 = 2;

							temp = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);
							if (temp.length() > 0) {

								try {

									row18.Taux_releve = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_12) {
									globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE",
											ex_tFileInputDelimited_12.getMessage());
									rowstate_tFileInputDelimited_12.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Taux_releve", "row18", temp, ex_tFileInputDelimited_12),
											ex_tFileInputDelimited_12));
								}

							} else {

								row18.Taux_releve = null;

							}

							columnIndexWithD_tFileInputDelimited_12 = 3;

							temp = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);
							if (temp.length() > 0) {

								try {

									row18.Seuil_tolere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_12) {
									globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE",
											ex_tFileInputDelimited_12.getMessage());
									rowstate_tFileInputDelimited_12.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Seuil_tolere", "row18", temp, ex_tFileInputDelimited_12),
											ex_tFileInputDelimited_12));
								}

							} else {

								row18.Seuil_tolere = null;

							}

							columnIndexWithD_tFileInputDelimited_12 = 4;

							row18.Type = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);

							columnIndexWithD_tFileInputDelimited_12 = 5;

							temp = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);
							if (temp.length() > 0) {

								try {

									row18.ID_Capteur = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_12) {
									globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE",
											ex_tFileInputDelimited_12.getMessage());
									rowstate_tFileInputDelimited_12.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Capteur", "row18", temp, ex_tFileInputDelimited_12),
											ex_tFileInputDelimited_12));
								}

							} else {

								row18.ID_Capteur = null;

							}

							columnIndexWithD_tFileInputDelimited_12 = 6;

							temp = fid_tFileInputDelimited_12.get(columnIndexWithD_tFileInputDelimited_12);
							if (temp.length() > 0) {

								try {

									row18.ID_Station = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_12) {
									globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE",
											ex_tFileInputDelimited_12.getMessage());
									rowstate_tFileInputDelimited_12.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID_Station", "row18", temp, ex_tFileInputDelimited_12),
											ex_tFileInputDelimited_12));
								}

							} else {

								row18.ID_Station = null;

							}

							if (rowstate_tFileInputDelimited_12.getException() != null) {
								throw rowstate_tFileInputDelimited_12.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_12_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_12 = true;

							System.err.println(e.getMessage());
							row18 = null;

						}

						/**
						 * [tFileInputDelimited_12 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_12 main ] start
						 */

						currentComponent = "tFileInputDelimited_12";

						tos_count_tFileInputDelimited_12++;

						/**
						 * [tFileInputDelimited_12 main ] stop
						 */

						/**
						 * [tFileInputDelimited_12 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_12";

						/**
						 * [tFileInputDelimited_12 process_data_begin ] stop
						 */
// Start of branch "row18"
						if (row18 != null) {

							/**
							 * [tUnite_5 main ] start
							 */

							currentComponent = "tUnite_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row18"

								);
							}

//////////

// for output
							row16 = new row16Struct();

							row16.ID_Polluant = row18.ID_Polluant;
							row16.Date = row18.Date;
							row16.Taux_releve = row18.Taux_releve;
							row16.Seuil_tolere = row18.Seuil_tolere;
							row16.Type = row18.Type;
							row16.ID_Capteur = row18.ID_Capteur;
							row16.ID_Station = row18.ID_Station;

							nb_line_tUnite_5++;

//////////

							tos_count_tUnite_5++;

							/**
							 * [tUnite_5 main ] stop
							 */

							/**
							 * [tUnite_5 process_data_begin ] start
							 */

							currentComponent = "tUnite_5";

							/**
							 * [tUnite_5 process_data_begin ] stop
							 */

							/**
							 * [tMap_5 main ] start
							 */

							currentComponent = "tMap_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row16"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_5 = false;
							boolean mainRowRejected_tMap_5 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row17"
							///////////////////////////////////////////////

							boolean forceLooprow17 = false;

							row17Struct row17ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_5 = false;

								row17HashKey.ID_Polluant = row16.ID_Polluant;

								row17HashKey.Date = row16.Date == null ? null
										: new java.util.Date(row16.Date.getTime());

								row17HashKey.hashCodeDirty = true;

								tHash_Lookup_row17.lookup(row17HashKey);

								if (!tHash_Lookup_row17.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_5 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row17 != null && tHash_Lookup_row17.getCount(row17HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
								// 'row17' and it contains more one result from keys : row17.ID_Polluant = '" +
								// row17HashKey.ID_Polluant + "', row17.Date = '" + row17HashKey.Date + "'");
							} // G 071

							row17Struct row17 = null;

							row17Struct fromLookup_row17 = null;
							row17 = row17Default;

							if (tHash_Lookup_row17 != null && tHash_Lookup_row17.hasNext()) { // G 099

								fromLookup_row17 = tHash_Lookup_row17.next();

							} // G 099

							if (fromLookup_row17 != null) {
								row17 = fromLookup_row17;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_5__Struct Var = Var__tMap_5;
								Var.var1 = (row17.somme / (row17.NBS + row17.NBC));
								Var.var2 = Var.var1 > row16.Seuil_tolere ? "Alerte Pollution" : "Normal";// ###############################
								// ###############################
								// # Output tables

								out3 = null;

								if (!rejectedInnerJoin_tMap_5) {

// # Output table : 'out3'
									out3_tmp.ID_Polluant = row16.ID_Polluant;
									out3_tmp.Taux_Moyen_Jour = Var.var1;
									out3_tmp.NBS = row17.NBS;
									out3_tmp.NBC = row17.NBC;
									out3_tmp.Statut = Var.var2;
									out3 = out3_tmp;
								} // closing inner join bracket (2)
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_5 = false;

							tos_count_tMap_5++;

							/**
							 * [tMap_5 main ] stop
							 */

							/**
							 * [tMap_5 process_data_begin ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_begin ] stop
							 */
// Start of branch "out3"
							if (out3 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out3"

									);
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[5];

								if (out3.ID_Polluant != null) { //
									row_tLogRow_1[0] = String.valueOf(out3.ID_Polluant);

								} //

								row_tLogRow_1[1] = FormatterUtils.formatUnwithE(out3.Taux_Moyen_Jour);

								if (out3.NBS != null) { //
									row_tLogRow_1[2] = String.valueOf(out3.NBS);

								} //

								if (out3.NBC != null) { //
									row_tLogRow_1[3] = String.valueOf(out3.NBC);

								} //

								if (out3.Statut != null) { //
									row_tLogRow_1[4] = String.valueOf(out3.Statut);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "out3"

							/**
							 * [tMap_5 process_data_end ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_end ] stop
							 */

							/**
							 * [tUnite_5 process_data_end ] start
							 */

							currentComponent = "tUnite_5";

							/**
							 * [tUnite_5 process_data_end ] stop
							 */

						} // End of branch "row18"

						/**
						 * [tFileInputDelimited_12 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_12";

						/**
						 * [tFileInputDelimited_12 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_12 end ] start
						 */

						currentComponent = "tFileInputDelimited_12";

					}
				} finally {
					if (!((Object) ("C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_12 != null) {
							fid_tFileInputDelimited_12.close();
						}
					}
					if (fid_tFileInputDelimited_12 != null) {
						globalMap.put("tFileInputDelimited_12_NB_LINE", fid_tFileInputDelimited_12.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_12", true);
				end_Hash.put("tFileInputDelimited_12", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_12 end ] stop
				 */

				/**
				 * [tUnite_5 end ] start
				 */

				currentComponent = "tUnite_5";

				globalMap.put("tUnite_5_NB_LINE", nb_line_tUnite_5);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row18", "row14");
				}

				ok_Hash.put("tUnite_5", true);
				end_Hash.put("tUnite_5", System.currentTimeMillis());

				/**
				 * [tUnite_5 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row17 != null) {
					tHash_Lookup_row17.endGet();
				}
				globalMap.remove("tHash_Lookup_row17");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row16");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out3");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_5"
			globalMap.remove("tHash_Lookup_row17");

			try {

				/**
				 * [tFileInputDelimited_11 finally ] start
				 */

				currentComponent = "tFileInputDelimited_11";

				/**
				 * [tFileInputDelimited_11 finally ] stop
				 */

				/**
				 * [tFileInputDelimited_12 finally ] start
				 */

				currentComponent = "tFileInputDelimited_12";

				/**
				 * [tFileInputDelimited_12 finally ] stop
				 */

				/**
				 * [tUnite_5 finally ] start
				 */

				currentComponent = "tUnite_5";

				/**
				 * [tUnite_5 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_11_SUBPROCESS_STATE", 1);
	}

	public void tFileOutputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileOutputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_1.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 main ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				tos_count_tFileOutputDelimited_1++;

				/**
				 * [tFileOutputDelimited_1 main ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 process_data_begin ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				/**
				 * [tFileOutputDelimited_1 process_data_begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 process_data_end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				/**
				 * [tFileOutputDelimited_1 process_data_end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileOutputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public void tFileOutputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileOutputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tFileOutputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_4", false);
				start_Hash.put("tFileOutputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_4";

				int tos_count_tFileOutputDelimited_4 = 0;

				String fileName_tFileOutputDelimited_4 = "";
				fileName_tFileOutputDelimited_4 = (new java.io.File(
						"C:/Users/yannt/Downloads/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/out.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_4 = null;
				String extension_tFileOutputDelimited_4 = null;
				String directory_tFileOutputDelimited_4 = null;
				if ((fileName_tFileOutputDelimited_4.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_4.lastIndexOf(".") < fileName_tFileOutputDelimited_4
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4;
						extension_tFileOutputDelimited_4 = "";
					} else {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
								fileName_tFileOutputDelimited_4.lastIndexOf("."));
						extension_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4
								.substring(fileName_tFileOutputDelimited_4.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
							fileName_tFileOutputDelimited_4.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_4.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
								fileName_tFileOutputDelimited_4.lastIndexOf("."));
						extension_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4
								.substring(fileName_tFileOutputDelimited_4.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4;
						extension_tFileOutputDelimited_4 = "";
					}
					directory_tFileOutputDelimited_4 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_4 = true;
				java.io.File filetFileOutputDelimited_4 = new java.io.File(fileName_tFileOutputDelimited_4);
				globalMap.put("tFileOutputDelimited_4_FILE_NAME", fileName_tFileOutputDelimited_4);
				if (filetFileOutputDelimited_4.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_4.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_4 = 0;
				int splitedFileNo_tFileOutputDelimited_4 = 0;
				int currentRow_tFileOutputDelimited_4 = 0;

				final String OUT_DELIM_tFileOutputDelimited_4 = /** Start field tFileOutputDelimited_4:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_4:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_4 = /**
																		 * Start field
																		 * tFileOutputDelimited_4:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_4:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_4 != null && directory_tFileOutputDelimited_4.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_4 = new java.io.File(directory_tFileOutputDelimited_4);
					if (!dir_tFileOutputDelimited_4.exists()) {
						dir_tFileOutputDelimited_4.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_4 = null;

				java.io.File fileToDelete_tFileOutputDelimited_4 = new java.io.File(fileName_tFileOutputDelimited_4);
				if (fileToDelete_tFileOutputDelimited_4.exists()) {
					fileToDelete_tFileOutputDelimited_4.delete();
				}
				outtFileOutputDelimited_4 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_4, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_4", outtFileOutputDelimited_4);
				resourceMap.put("nb_line_tFileOutputDelimited_4", nb_line_tFileOutputDelimited_4);

				/**
				 * [tFileOutputDelimited_4 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 main ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				tos_count_tFileOutputDelimited_4++;

				/**
				 * [tFileOutputDelimited_4 main ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 process_data_begin ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				/**
				 * [tFileOutputDelimited_4 process_data_begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 process_data_end ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				/**
				 * [tFileOutputDelimited_4 process_data_end ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 end ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				if (outtFileOutputDelimited_4 != null) {
					outtFileOutputDelimited_4.flush();
					outtFileOutputDelimited_4.close();
				}

				globalMap.put("tFileOutputDelimited_4_NB_LINE", nb_line_tFileOutputDelimited_4);
				globalMap.put("tFileOutputDelimited_4_FILE_NAME", fileName_tFileOutputDelimited_4);

				resourceMap.put("finish_tFileOutputDelimited_4", true);

				ok_Hash.put("tFileOutputDelimited_4", true);
				end_Hash.put("tFileOutputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_4 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileOutputDelimited_4 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				if (resourceMap.get("finish_tFileOutputDelimited_4") == null) {

					java.io.Writer outtFileOutputDelimited_4 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_4");
					if (outtFileOutputDelimited_4 != null) {
						outtFileOutputDelimited_4.flush();
						outtFileOutputDelimited_4.close();
					}

				}

				/**
				 * [tFileOutputDelimited_4 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileOutputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final stations stationsClass = new stations();

		int exitCode = stationsClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = stations.class.getClassLoader()
					.getResourceAsStream("m2_data_quality_project/stations_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = stations.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_11Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_11) {
			globalMap.put("tFileInputDelimited_11_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_11.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : stations");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 250586 characters generated by Talend Open Studio for Data Integration on the
 * 16 octobre 2024, 23:32:18 CEST
 ************************************************************************************************/