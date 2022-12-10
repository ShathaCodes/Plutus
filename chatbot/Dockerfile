FROM python:3.10
WORKDIR /chatbot
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
EXPOSE 5000
CMD ["python", "train.py"; "python", "run.py"]