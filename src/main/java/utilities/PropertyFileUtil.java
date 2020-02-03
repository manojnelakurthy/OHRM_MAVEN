package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
public static String getvalueforkey(String key) throws Throwable
{
	Properties configproperties=new Properties();
	FileInputStream fi=new FileInputStream("G:\\Practice\\OHRM_HYBRID\\Property File\\Environment.properties");
	configproperties.load(fi);
	String key1=configproperties.getProperty(key);
	return key1;
}
}
