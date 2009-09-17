<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">

<xsl:template match="/">
	<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
		<fo:layout-master-set>
			<fo:simple-page-master 
		                  page-height="29.7cm" 
		                  page-width="21cm"
		                  margin-top="1cm" 
		                  margin-bottom="2cm" 
		                  margin-left="1cm" 
		                  margin-right="1cm"
		                  master-name="PageMaster">
		      <fo:region-body margin-top="3cm"/>
		      <fo:region-before extent="3cm"/>
		      <fo:region-after extent="1.5cm"/>
		    </fo:simple-page-master>
		</fo:layout-master-set>
	
	  	<fo:page-sequence master-reference="PageMaster">
			<!-- Logotipo  cipion -->
			<fo:static-content flow-name="xsl-region-before">
				<fo:block font-size="8pt">
					<fo:external-graphic 
					   content-width="scale-to-fit"
                       width="3cm"
                       content-height="100%"
                       scaling="uniform" 
					   src="url('images/cipionreportlogo.png')"/>
				</fo:block>
				<fo:block font-size="8pt" top="0cm" left="15cm" position="absolute" text-align="right">
					<fo:external-graphic
					      content-width="scale-to-fit"
	                      width="3cm"
	                      content-height="100%"
	                      scaling="uniform" 
					      src="url({//logoImage})"/>
				</fo:block>
			</fo:static-content>
			<xsl:apply-templates />
		</fo:page-sequence>
	</fo:root>
</xsl:template>
<xsl:template match="Datos">
		    <fo:flow flow-name="xsl-region-body">
				<xsl:apply-templates select='division' /> 
			</fo:flow>
</xsl:template>
<xsl:template match="division">
					<!--  Cabecera -->
					<fo:block font-size="18pt" 
					  font-family="sans-serif" 
					  line-height="24pt"
					  space-after.optimum="15pt"
					  text-align="center"
					  padding-top="3pt">
					  <xsl:value-of select="grade"/> | <xsl:value-of select="category"/> | Manga <xsl:value-of select="round"/> 
					</fo:block>

						<fo:table border-width="0.5pt" border-style="solid" border-color="#888888" start-indent="1em" end-indent="1em"  space-after="2em">
							<fo:table-column column-width="1cm"/>
							<fo:table-column column-width="1cm"/>
							<fo:table-column column-width="5cm"/>
							<fo:table-column column-width="5cm"/>
							<fo:table-column column-width="5cm"/>
							<fo:table-column column-width="2cm"/>
								<fo:table-body>
									<fo:table-row border-color="#888888">
										<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
											<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Orden</fo:block>
										</fo:table-cell>
										<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
											<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Dorsal</fo:block>
										</fo:table-cell>
										<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
											<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Perro</fo:block>
										</fo:table-cell>
										<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
											<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Guía</fo:block>
										</fo:table-cell>
										<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
											<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Club</fo:block>
										</fo:table-cell>
										<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
											<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Celo</fo:block>
										</fo:table-cell>
									</fo:table-row>
								
								<xsl:apply-templates select='Participante' /> 
								</fo:table-body>
							</fo:table>		
							<?hard-pagebreak?>
</xsl:template>
<xsl:template match="Participante">
									<fo:table-row border-color="#888888">
										<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
											<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
												<xsl:value-of select="order"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
											<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
												<xsl:value-of select="dorsal"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
											<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
												<xsl:value-of select="dog"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
											<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
												<xsl:value-of select="guide"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
											<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
												<xsl:value-of select="club"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
											<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
												<xsl:if test="heat='true'">Celo</xsl:if> 
											</fo:block>
										</fo:table-cell>
									</fo:table-row>

</xsl:template>
<xsl:template match="processing-instruction('hard-pagebreak')">
   <fo:block break-after='page'/>
</xsl:template>
</xsl:stylesheet>