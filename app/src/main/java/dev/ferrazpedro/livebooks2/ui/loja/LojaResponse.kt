package dev.ferrazpedro.livebooks2.ui.loja

class LojaResponse(
    val status: LojaStatus,
    val data: Any?,
    val error: Throwable?
) {

    companion object {
        fun loading(): LojaResponse {
            return LojaResponse(
                LojaStatus.LOADING,
                null,
                null
            )
        }

        fun <T : Any> success(data: T): LojaResponse {
            return LojaResponse(
                LojaStatus.SUCCESS,
                data,
                null
            )
        }

        fun error(error: Throwable): LojaResponse {
            return LojaResponse(
                LojaStatus.ERROR,
                null,
                error
            )
        }

        fun empty(): LojaResponse {
            return LojaResponse(
                LojaStatus.EMPTY_RESPONSE,
                null,
                null
            )
        }
    }
}