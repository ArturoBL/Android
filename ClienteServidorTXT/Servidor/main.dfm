object Form1: TForm1
  Left = 358
  Top = 230
  Width = 1305
  Height = 675
  Caption = 'Form1'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object mmo: TMemo
    Left = 24
    Top = 24
    Width = 625
    Height = 353
    ScrollBars = ssBoth
    TabOrder = 0
    WordWrap = False
  end
  object ss: TServerSocket
    Active = True
    Port = 90
    ServerType = stNonBlocking
    OnListen = ssListen
    OnAccept = ssAccept
    OnClientRead = ssClientRead
    Left = 200
    Top = 88
  end
end
