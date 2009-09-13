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
			
		    <fo:flow flow-name="xsl-region-body">
			  <!--  Cabecera -->
			  <fo:block font-size="18pt" 
		            font-family="sans-serif" 
		            line-height="24pt"
		            space-after.optimum="15pt"
		            text-align="center"
		            padding-top="3pt">
		        Informe Resultados de la Prueba 
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
		      
			  <!--  Listado de Participantes -->
				<xsl:apply-templates select="//Participantes"/>

			  <!--  Resultados -->		      
				<xsl:apply-templates select="//Resultados"/>
		     </fo:flow>
	    </fo:page-sequence>
	</fo:root>
</xsl:template>
<xsl:template match="Resultados">
		  <!--  Cabecera -->
		  <fo:block 
		  		font-size="14pt" 
	            font-family="sans-serif" 
	            text-align="center">
	        ------ Resultados ------ 
	      </fo:block>

				<!--  Cabecera datos de resultados -->
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

		<!--  Tabla de Resultados -->
		<fo:table border-width="0.5pt" border-style="solid" border-color="#888888" start-indent="1em" end-indent="1em"  space-after="2em">
			<fo:table-column column-width="1cm"/>
			<fo:table-column column-width="5cm"/>
			<fo:table-column column-width="5cm"/>
			<fo:table-column column-width="2cm"/>
			<fo:table-column column-width="2cm"/>
			<fo:table-column column-width="2cm"/>
				<fo:table-body>
					<fo:table-row border-color="#888888">
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">P</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Guía</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Perro</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Penal. Recorr.</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Penal. Tiempo</fo:block>
						</fo:table-cell>
						<fo:table-cell border-width="0.5pt" border-color="#888888" border-style="flat" background-color="#EEEEEE">
							<fo:block text-align="center" font-size="12pt" color="black" font-weight="bold">Penal. Total</fo:block>
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
								<xsl:value-of select="PathFaultPoints"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="TimeFaultPoints"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-style="solid" border-color="#888888" background-color="white" display-align="center">
							<fo:block font-size="10pt" font-family="sans-serif" text-align="center">
								<xsl:value-of select="TotalFaultPoints"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
				</fo:table-body>
			</fo:table>
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