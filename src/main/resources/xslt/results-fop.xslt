<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">

<xsl:template match="/">
	<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
		<fo:layout-master-set>
			<fo:simple-page-master 
		                  page-height="21cm" 
		                  page-width="29.7cm"
		                  margin-top="0.5cm" 
		                  margin-bottom="0.5cm" 
		                  margin-left="0.5cm" 
		                  margin-right="0.5cm"
		                  master-name="PageMaster">
		      <fo:region-body margin-top="0.5cm"/>
		      <fo:region-before extent="0.5cm"/>
		      <fo:region-after extent="0.5cm"/>
		    </fo:simple-page-master>
		</fo:layout-master-set>
	
	  	<fo:page-sequence master-reference="PageMaster">
			<!-- Logotipo  cipion -->
			<fo:static-content flow-name="xsl-region-before">
				<fo:block font-size="8pt">
					<fo:table border-width="0pt" border-style="solid" start-indent="0em" end-indent="0em"  space-after="0em">
						<fo:table-column column-width="3cm"/>
						<fo:table-column column-width="22cm"/>
						<fo:table-column column-width="3cm"/>
							<fo:table-body>
								<fo:table-row border-color="#888888">
									<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#FFFFFF">
										<fo:block text-align="left" font-size="8pt" color="black" font-weight="bold">
										<!--  
											<fo:external-graphic 
											   content-width="scale-to-fit"
						                       width="3cm"
						                       content-height="100%"
						                       scaling="uniform" 
											   src="url('images/cipionreportlogo.png')"/>
										-->
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#FFFFFF">
										<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold"> 
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#FFFFFF">
										<fo:block text-align="right" font-size="8pt" color="black" font-weight="bold">
											<fo:external-graphic
											      content-width="scale-to-fit"
							                      width="3cm"
							                      content-height="100%"
							                      scaling="uniform" 
												  display-align="after" 
												  src="url('images/logoreport.jpg')"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
				</fo:block>
			</fo:static-content>
			<xsl:apply-templates />
	    </fo:page-sequence>
	</fo:root>
</xsl:template>
<xsl:template match="Datos">
		    <fo:flow flow-name="xsl-region-body">
			  <!--  Resultados -->		      
				<xsl:apply-templates select="//Resultados"/>
		     </fo:flow>
</xsl:template>
<xsl:template match="Resultados">
			  <!--  Cabecera -->
			  <fo:block font-size="18pt" 
		            font-family="sans-serif" 
		            line-height="24pt"
		            space-after.optimum="15pt"
		            text-align="center"
		            padding-top="3pt">
		        FINAL <xsl:value-of select="Grade"/> | <xsl:value-of select="Category"/>
		      </fo:block>
		      
				<fo:table space-before="2em" space-after="2em">
					 <fo:table-column column-width="30mm"/>
					 <fo:table-column column-width="100mm"/>
					 <fo:table-column column-width="40mm"/>
					 <fo:table-body>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Organizador:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm"><xsl:value-of select="//Organizador"/></fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Fecha:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm"><xsl:value-of select="//Fecha"/></fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Evento:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm"><xsl:value-of select="//Evento"/></fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Juez:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm">ValorPrueba2</fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					 </fo:table-body>
				</fo:table>

				<!--  Cabecera datos de resultados -->
				<!-- 
				<fo:table space-before="2em"  space-after="2em">
					 <fo:table-column column-width="30mm"/>
					 <fo:table-column column-width="100mm"/>
					 <fo:table-column column-width="40mm"/>
					 <fo:table-body>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Manga:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm"><xsl:value-of select="Round"/></fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Grado:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm"><xsl:value-of select="Grade"/></fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					   <fo:table-row>
					     <fo:table-cell>
					         <fo:block text-align="right" font-weight="bold">Categoría:</fo:block>
					     </fo:table-cell>
					     <fo:table-cell padding-start="3mm">
					     	<fo:block text-align="left">
					             <fo:block padding-start="3mm" padding-end="3mm"><xsl:value-of select="Category"/></fo:block>
				             </fo:block>
					     </fo:table-cell>
					   </fo:table-row>
					 </fo:table-body>
				</fo:table>	      
				-->
		<!--  Tabla de Resultados -->
		<fo:table border-width="0.5pt" border-style="solid" border-color="#888888" start-indent="1em" end-indent="1em"  space-after="2em">
			<fo:table-column column-width="1cm"/>
			<fo:table-column column-width="1cm"/>
			<fo:table-column column-width="2cm"/>
			<fo:table-column column-width="4.5cm"/>
			<fo:table-column column-width="1.5cm"/>
			<fo:table-column column-width="1.5cm"/>
			<fo:table-column column-width="2cm"/>
			<fo:table-column column-width="1.5cm"/>
			<fo:table-column column-width="1.5cm"/>
			<fo:table-column column-width="2cm"/>
			<fo:table-column column-width="1.5cm"/>
			<fo:table-column column-width="1.5cm"/>
			<fo:table-column column-width="3cm"/>
			<fo:table-column column-width="2cm"/>
			<fo:table-column column-width="2cm"/>
				<fo:table-body>
					<fo:table-row border-color="#888888">
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Clasif.</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Dorsal</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="10pt" color="black" font-weight="bold">Perro</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="10pt" color="black" font-weight="bold">Guía</fo:block>
						</fo:table-cell>
						
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Pen.M1</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Tiem.M1</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Calif.M1</fo:block>
						</fo:table-cell>

						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Pen.M2</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Tiem.M2</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Calif.M2</fo:block>
						</fo:table-cell>

						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Pen.Final</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Tiem.Final</fo:block>
						</fo:table-cell>

						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="10pt" color="black" font-weight="bold">Club</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Lic.</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="8pt" color="black" font-weight="bold">Categ.</fo:block>
						</fo:table-cell>
					</fo:table-row>
				<xsl:for-each select="Resultado">
					<fo:table-row border-color="#888888">
					
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Position"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Dorsal"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Dog"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Guide"/>
							</fo:block>
						</fo:table-cell>
						
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="FaultPoints1"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Time1"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="8pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Calification1"/>
							</fo:block>
						</fo:table-cell>

						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="FaultPoints2"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Time2"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="8pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Calification2"/>
							</fo:block>
						</fo:table-cell>

						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="FaultPoints3"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Time3"/>
							</fo:block>
						</fo:table-cell>
						
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="8pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Club"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="License"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="8pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Category"/>
							</fo:block>
						</fo:table-cell>
 					</fo:table-row>
				</xsl:for-each>
				</fo:table-body>
			</fo:table>
			<xsl:if test="last='false'">
					<fo:block break-after='page'/>
			</xsl:if> 
</xsl:template>
<xsl:template match="Participantes">
		  <!--  Cabecera -->
		  <fo:block 
		  		font-size="14pt" 
	            font-family="sans-serif" 
	            text-align="center">
	        Partipantes de la Prueba 
	      </fo:block>
		<fo:table border-width="0.5pt" border-style="solid" border-color="#888888" start-indent="1em" end-indent="1em"  space-after="2em">
			<fo:table-column column-width="5cm"/>
			<fo:table-column column-width="3cm"/>
			<fo:table-column column-width="3cm"/>
			<fo:table-column column-width="2.5cm"/>
			<fo:table-column column-width="5cm"/>
				<fo:table-body>
					<fo:table-row border-color="#888888">
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Guía</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Perro</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Grado</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Categoría</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Club</fo:block>
						</fo:table-cell>
					</fo:table-row>
				<xsl:for-each select="Participante">
					<fo:table-row border-color="#888888">
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Guide"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Dog"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Grade"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Category"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="Club"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
				</fo:table-body>
			</fo:table>	
</xsl:template>
</xsl:stylesheet>