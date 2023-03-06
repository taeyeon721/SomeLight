from django.shortcuts import render

from django.conf import settings
from accounts.models import User

from allauth.socialaccount.models import SocialAccount
from dj_rest_auth.registration.views import SocialLoginView
from allauth.socialaccount.providers.naver import views as naver_view
from allauth.socialaccount.providers.oauth2.client import OAuth2Client

from django.http import JsonResponse
import requests
from rest_framework import status
from json.decoder import JSONDecodeError

state = getattr(settings, 'STATE')

BASE_URL = 'http://localhost:8000/'

NAVER_CALLBACK_URI = BASE_URL + 'accounts/naver/callback'

# Create your views here.

class NaverLogin(SocialLoginView):
    adapter_class = NaverOAuth2Adapter
    callback_url = NAVER_CALLBACK_URI
    client_class = OAuth2Client
    serializer_class = SocialLoginSerializer