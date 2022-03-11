package com.deyvi.realogyassesment.data.use_case

import com.deyvi.realogyassesment.common.Constants.ERROR_HTTP_EXCEPTION
import com.deyvi.realogyassesment.common.Constants.ERROR_IO_EXCEPTION
import com.deyvi.realogyassesment.common.Constants.ERROR_UNKNOWN_EXCEPTION
import com.deyvi.realogyassesment.common.Resource
import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic
import com.deyvi.realogyassesment.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repo: CharactersRepository
) {

    operator fun invoke()
            : Flow<Resource<List<RelatedTopic>>> = flow {
        try {
            emit(Resource.Loading<List<RelatedTopic>>())
            val characters = repo.getCharacters()
            emit(Resource.Success<List<RelatedTopic>>(characters))
        } catch (e: HttpException) {
            emit(Resource.Error<List<RelatedTopic>>(e.localizedMessage ?: ERROR_HTTP_EXCEPTION))
        } catch (e: IOException) {
            emit(Resource.Error<List<RelatedTopic>>(ERROR_IO_EXCEPTION))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<RelatedTopic>>(e.localizedMessage ?: ERROR_UNKNOWN_EXCEPTION)
            )
        }
    }
}