# profiles/urls.py
from django.urls import path
from .views import UserRegistrationView
from rest_framework_simplejwt.views import (TokenObtainPairView, TokenRefreshView,)
from .views import UserRegistrationView, ActivateAccount

urlpatterns = [
    path('register/', UserRegistrationView.as_view(), name='user-registration'),
    path('login/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('activate/<str:uidb64>/<str:token>/', ActivateAccount.as_view(), name='activate'),
]
