package com.example.a2024accgr1amar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2024accgr1amar.EmailItem
import com.example.a2024accgr1amar.R

class HomeViewModel : ViewModel() {

    // Lista de elementos para el RecyclerView
    private val _items = MutableLiveData<List<EmailItem>>().apply {
        value = listOf(
            EmailItem("Promoción 50% de descuento", "No te pierdas esta oferta exclusiva", R.drawable.ic_android_green),
            EmailItem("Oferta de fin de semana", "Descuentos increíbles solo por este fin de semana", R.drawable.ic_android_purple),
            EmailItem("Nuevo mensaje de Juan Pérez", "Hola, ¿qué tal?", R.drawable.ic_android_orange),
            EmailItem("Actualización de tu cuenta", "Se ha realizado un cambio en tu cuenta", R.drawable.ic_android_yellow),
            EmailItem("Recibo de pago", "Tu recibo de pago está disponible", R.drawable.ic_android_red),
            EmailItem("Evento próximo", "No te pierdas el evento de esta semana", R.drawable.ic_android_blue),
            EmailItem("Sorteo especial", "Participa en nuestro sorteo y gana premios", R.drawable.ic_android_purple),
            EmailItem("Aviso importante de seguridad", "Actualiza tu contraseña para mantener tu cuenta segura", R.drawable.ic_android_green),
            EmailItem("Invitación a conferencia", "Únete a nuestra conferencia virtual", R.drawable.ic_android_yellow),
            EmailItem("Recordatorio de cita", "Tienes una cita programada para mañana", R.drawable.ic_android_pink)
        )
    }
    val items: LiveData<List<EmailItem>> = _items
}
