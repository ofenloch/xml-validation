#!/bin/bash


JAVACLASS="xsdfiles"
FILENAME=${JAVACLASS}.java

XSDDIRECTORY="./data/schemas/2001"

mkdir -p ${XSDDIRECTORY}

curl https://www.w3.org/2001/03/xml.xsd  > ${XSDDIRECTORY}/xml.xsd
## curl http://www.w3.org/2009/XMLSchema/XMLSchema.dtd > ${XSDDIRECTORY}/XMLSchema.dtd
curl http://www.w3.org/2001/XMLSchema.dtd > ${XSDDIRECTORY}/XMLSchema.dtd
## curl http://www.w3.org/2009/XMLSchema/datatypes.dtd > ${XSDDIRECTORY}/datatypes.dtd
curl http://www.w3.org/2001/datatypes.dtd > ${XSDDIRECTORY}/datatypes.dtd
## curl http://www.w3.org/2009/XMLSchema/XMLSchema.xsd > ${XSDDIRECTORY}/XMLSchema.xsd
curl http://www.w3.org/2001/XMLSchema.xsd > ${XSDDIRECTORY}/XMLSchema.xsd








echo "public class ${JAVACLASS} { " > ${FILENAME}

echo "  Schema ooxmlSchema = factory.newSchema(new Source[] { " >> ${FILENAME}

for f in ${XSDDIRECTORY}/*.xsd ; do
  echo "  new StreamSource(new FileInputStream(\"${f}\"))," >> ${FILENAME}
done

echo "  });" >> ${FILENAME}

echo "} // public class ${JAVACLASS} " >> ${FILENAME}