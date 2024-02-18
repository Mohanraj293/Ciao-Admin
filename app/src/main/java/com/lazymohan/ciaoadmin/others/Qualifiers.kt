package com.lazymohan.ciaoadmin.others

import javax.inject.Qualifier

object Qualifiers {
  @Retention(AnnotationRetention.BINARY)
  @Qualifier
  annotation class IoDispatcher

  @Retention(AnnotationRetention.BINARY)
  @Qualifier
  annotation class MainDispatcher

  @Retention(AnnotationRetention.BINARY)
  @Qualifier
  annotation class DefaultDispatcher
}