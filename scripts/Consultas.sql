--

SELECT
		ParametrosMast.Texto "texto",
		ParametrosMast.DescripcionParametro "descripcionparametro",
		ParametrosMast.Numero "numero",
		ParametrosMast.Explicacion "explicacion"
FROM ParametrosMast
WHERE (
ParametrosMast.CompaniaCodigo =
:par_company ) AND
(
ParametrosMast.AplicacionCodigo = :par_application
) AND
(
ParametrosMast.ParametroClave = :par_key )