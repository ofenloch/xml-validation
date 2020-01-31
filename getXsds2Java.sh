#!/bin/bash

JAVACLASS="xsdfiles"
FILENAME=${JAVACLASS}.java

XSDDIRECTORY="/home/ofenloch/workspaces/jee/xml-validation/data/OfficeOpenXML-XMLSchema"

echo "public class ${JAVACLASS} { " > ${FILENAME}

echo "  Schema ooxmlSchema = factory.newSchema(new Source[] { " >> ${FILENAME}

for f in ${XSDDIRECTORY}/*.xsd ; do
  echo "  new StreamSource(new FileInputStream(\"${f}\"))," >> ${FILENAME}
done

echo "  });" >> ${FILENAME}

echo "} // public class ${JAVACLASS} " >> ${FILENAME}