package com.kursx.chart.domain.usecase

import com.kursx.chart.domain.model.PointDomainModel
import com.kursx.chart.domain.repository.PointsRepository
import javax.inject.Inject

interface ReceivePointsUseCase : suspend (Int) -> Result<List<PointDomainModel>>

class ReceivePointsUseCaseImpl @Inject constructor(
    private val pointsRepository: PointsRepository,
) : ReceivePointsUseCase {

    override suspend fun invoke(count: Int) = runCatching {
        pointsRepository.receive(count)
    }
}
