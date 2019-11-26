package br.com.caelum.ingresso.validation

import br.com.caelum.ingresso.model.Sessao
import java.time.LocalDate
import java.time.LocalDateTime

class SessionManager(roomSessions : List<Sessao>){

    val roomSessions = roomSessions

    private fun timeIsValid(existingSession: Sessao, newSession: Sessao): Boolean{

        val today : LocalDate = LocalDate.now()

        val currentSessionTime : LocalDateTime = existingSession.horario.atDate(today)
        val newSessionTime : LocalDateTime = newSession.horario.atDate(today)

        val isBefore : Boolean = currentSessionTime.isBefore(newSessionTime)

        if (isBefore)
            return newSessionTime.plus(newSession.filme.duracao).isBefore(currentSessionTime)
        else
            return currentSessionTime.plus(existingSession.filme.duracao).isBefore(newSessionTime)
    }

    fun fits(newSession: Sessao):Boolean{
        return roomSessions.stream().map{
            timeIsValid(it,newSession)
        }.reduce(Boolean::and).orElse(true);
    }

}